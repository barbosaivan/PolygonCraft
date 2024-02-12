package com.ivanbarbosa.polygoncraft.utils

import com.ivanbarbosa.polygoncraft.data.entities.Point
import kotlin.math.cos
import kotlin.math.sin


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.utils
* Create by Ivan Barbosa on 7/02/2024 at 9:05 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

fun MutableList<Point>.calculateCoordinates(
    sides: Int,
    displacement: Double = 0.45
) {
    val initialAngle = 0.0
    val angleStep = 360.0 / sides
    for (i in 0 until sides) {
        val angle = initialAngle + i * angleStep
        val x = (cos(Math.toRadians(angle)) * displacement) + displacement
        val y = (sin(Math.toRadians(angle)) * displacement) + displacement
        this.add(Point(x, y))
    }
}

fun MutableList<Point>.calculateScalePoint(point: List<Point>, scale: Double) {
    point.forEach {
        val x = (it.x * scale)
        val y = (it.y * scale)
        this.add(Point(x, y))
    }
}

fun MutableList<Point>.investScalePoint(point: List<Point>, scale: Double) {
    point.forEach {
        val x = (it.x / scale)
        val y = (it.y / scale)
        this.add(Point(x, y))
    }
}

