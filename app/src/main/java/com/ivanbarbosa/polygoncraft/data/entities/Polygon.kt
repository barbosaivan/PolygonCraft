package com.ivanbarbosa.polygoncraft.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.entities
* Create by Ivan Barbosa on 5/02/2024 at 7:19 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@Parcelize
data class Polygon(
    val name: String,
    var points: MutableList<Point>,
    val scale: Double = 0.73
) : Parcelable