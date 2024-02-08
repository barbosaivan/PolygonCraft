package com.ivanbarbosa.polygoncraft.utils

import android.util.Log
import com.ivanbarbosa.polygoncraft.data.entities.Point
import kotlin.math.cos
import kotlin.math.sin


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.utils
* Create by Ivan Barbosa on 7/02/2024 at 9:05 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

object HomeUtils {
    fun calculateCoordinates(
        sides: Int,
        scale: Double,
        displacement: Double,
    ): MutableList<Point> {
        val initialAngle = 0.0
        val angleStep = 360.0 / sides
        val points = mutableListOf<Point>()

        for (i in 0 until sides) {
            val angle = initialAngle + i * angleStep
            val x = (cos(Math.toRadians(angle)) * scale) + displacement
            val y = (sin(Math.toRadians(angle)) * scale) + displacement

            Log.i("(x,Y)", "($x, $y)")
            points.add(Point(x, y))
        }
        return points
    }
}