package com.ivanbarbosa.polygoncraft.home

import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.home
* Create by Ivan Barbosa on 8/02/2024 at 3:51 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
object FakeDataHome {
    fun generateFakePolygonsList(size: Int): List<Polygon> {
        val fakePolygons = mutableListOf<Polygon>()

        repeat(size) { index ->
            val fakeName = "Polygon $index"
            val fakePoints = generateFakePointsList((Math.random() * 15).toInt())
            fakePolygons.add(Polygon(fakeName, fakePoints.toMutableList()))
        }

        return fakePolygons
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