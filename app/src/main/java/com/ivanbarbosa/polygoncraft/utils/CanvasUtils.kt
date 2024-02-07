package com.ivanbarbosa.polygoncraft.utils

import android.content.Context


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 7/02/2024 at 5:31 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class CanvasUtils(private val context: Context) {

    private val aspectRatio: Float
        get() {
            val width = context.resources.displayMetrics.widthPixels.toFloat()
            val height = context.resources.displayMetrics.heightPixels.toFloat()
            return width / height
        }

    fun scaleInY(): Float = if (aspectRatio >= 1.0f) 0.5f else 0.5f * aspectRatio

    fun scaleInX(): Float = if (aspectRatio >= 1.0f) 0.5f / aspectRatio else 0.5f
}