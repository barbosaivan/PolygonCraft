package com.ivanbarbosa.polygoncraft.ui.desing

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.utils.CanvasUtils

/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 7/02/2024 at 2:35 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class CanvasDrawPolygon(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var polygon: Polygon? = null

    private val painter = CanvasPainter(canvasUtils = CanvasUtils(context))

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painter.drawGrid(canvas)
        polygon?.let { painter.drawPolygon(it, canvas) }
    }

    fun drawPolygon(polygon: Polygon) {
        this.polygon = polygon
        invalidate()
    }
}
