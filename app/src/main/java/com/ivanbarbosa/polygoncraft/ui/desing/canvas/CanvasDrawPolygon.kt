package com.ivanbarbosa.polygoncraft.ui.desing.canvas

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.utils.CanvasUtils
import kotlin.math.pow
import kotlin.math.sqrt

/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 7/02/2024 at 2:35 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class CanvasDrawPolygon(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var polygon: Polygon? = null
    private val canvasUtils = CanvasUtils(context)
    private val painter = CanvasPainter(canvasUtils)

    private var movingCircleIndex: Int = -1
    private var lastTouchX: Float = 0f
    private var lastTouchY: Float = 0f
    private val touchTolerance = 60f

    private val scaleInY: Float
        get() = canvasUtils.scaleInY()

    private val scaleInX: Float
        get() = canvasUtils.scaleInX()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painter.drawGrid(canvas)
        polygon?.let { painter.drawPolygon(it, canvas) }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> handleTouchDown(x, y)
            MotionEvent.ACTION_MOVE -> handleTouchMove(x, y)
            MotionEvent.ACTION_UP -> handleTouchUp()
        }

        return true
    }

    private fun handleTouchDown(x: Float, y: Float) {
        polygon?.let { polygon ->
            for ((index, point) in polygon.points.withIndex()) {
                val adjustedX = point.x * width * scaleInX
                val adjustedY = height - (point.y * height) * scaleInY
                val distance = calculateDistance(x, y, adjustedX.toFloat(), adjustedY.toFloat())

                if (distance < touchTolerance) {
                    movingCircleIndex = index
                    lastTouchX = x
                    lastTouchY = y
                    invalidate()
                    break
                }
            }
        }
    }

    private fun handleTouchMove(x: Float, y: Float) {
        if (movingCircleIndex != -1) {
            val deltaX = x - lastTouchX
            val deltaY = y - lastTouchY

            polygon?.let { polygon ->
                val movedPoint = polygon.points[movingCircleIndex]
                val newX = movedPoint.x + deltaX / width
                val newY = movedPoint.y - deltaY / height

                polygon.points[movingCircleIndex] = Point(newX, newY)
                lastTouchX = x
                lastTouchY = y
                invalidate()
            }
        }
    }

    private fun handleTouchUp() {
        movingCircleIndex = -1
    }

    private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Double {
        return sqrt((x1 - x2).pow(2).toDouble() + (y1 - y2).pow(2))
    }

    fun drawPolygon(polygon: Polygon) {
        this.polygon = polygon
        invalidate()
    }

    fun getPolygon(): Polygon? {
        return polygon
    }
}
