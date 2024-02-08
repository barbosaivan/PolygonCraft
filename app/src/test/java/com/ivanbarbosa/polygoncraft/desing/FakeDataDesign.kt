package com.ivanbarbosa.polygoncraft.desing

import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.desing
* Create by Ivan Barbosa on 8/02/2024 at 8:15 a. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
object FakeDataDesign {
    fun generateFakePolygons(): Polygon {
        val fakeName = "Polygon66"
        val fakePoints = generateFakePointsList((Math.random() * 15).toInt())
        return Polygon(fakeName, fakePoints.toMutableList())

    }

    private fun generateFakePointsList(size: Int): List<Point> {
        val fakePoints = mutableListOf<Point>()

        repeat(size) {
            val fakeX = Math.random() * 2
            val fakeY = Math.random() * 2
            fakePoints.add(Point(fakeX, fakeY))
        }

        return fakePoints
    }
}