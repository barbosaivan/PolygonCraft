package com.ivanbarbosa.polygoncraft.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.entities
* Create by Ivan Barbosa on 6/02/2024 at 10:31 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@Parcelize
data class Point(
    val x: Double,
    val y: Double
): Parcelable
