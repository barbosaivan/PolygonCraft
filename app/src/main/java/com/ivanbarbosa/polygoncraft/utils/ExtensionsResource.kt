package com.ivanbarbosa.polygoncraft.utils

import android.content.Context
import androidx.annotation.ArrayRes


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.utils
* Create by Ivan Barbosa on 11/02/2024 at 3:59 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

fun Context.getStringArray(@ArrayRes resourceId: Int): Array<String> {
    return resources.getStringArray(resourceId)
}