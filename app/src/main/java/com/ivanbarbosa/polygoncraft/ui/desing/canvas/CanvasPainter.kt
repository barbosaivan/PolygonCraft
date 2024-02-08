package com.ivanbarbosa.polygoncraft.ui.desing.canvas

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.utils.CanvasUtils


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.utils
* Create by Ivan Barbosa on 7/02/2024 at 5:49 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class CanvasPainter(private val canvasUtils: CanvasUtils) {

    private val paintCircle = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintGrid = Paint().apply {
        color = Color.GRAY
        strokeWidth = 2f
    }

    private val paintLine = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val scaleInY: Float
        get() = canvasUtils.scaleInY()

    private val scaleInX: Float
        get() = canvasUtils.scaleInX()

    fun drawPolygon(polygon: Polygon, canvas: Canvas) {
        val points = polygon.points
        points.forEachIndexed { index, point ->
            val adjustedX = point.x * canvas.width * scaleInX
            val adjustedY = canvas.height - (point.y * canvas.height * scaleInY)

            if (index < points.size - 1) {
                drawLineBetweenPoints(point, points[index + 1], canvas)
            } else if (index == points.size - 1 && points.size > 1) {
                drawLineBetweenPoints(point, points[0], canvas)
            }

            drawCircleAtPoint(adjustedX.toFloat(), adjustedY.toFloat(), canvas)
        }
    }

    private fun drawLineBetweenPoints(start: Point, end: Point, canvas: Canvas) {
        val adjustedStartX = start.x * canvas.width * scaleInX
        val adjustedStartY = canvas.height - (start.y * canvas.height * scaleInY)
        val adjustedEndX = end.x * canvas.width * scaleInX
        val adjustedEndY = canvas.height - (end.y * canvas.height * scaleInY)

        canvas.drawLine(
            adjustedStartX.toFloat(), adjustedStartY.toFloat(),
            adjustedEndX.toFloat(), adjustedEndY.toFloat(),
            paintLine
        )
    }

    private fun drawCircleAtPoint(x: Float, y: Float, canvas: Canvas) {
        canvas.drawCircle(x, y, 50f, paintCircle)
    }

    fun drawGrid(canvas: Canvas) {
        val cellSize = 54
        val width = canvas.width
        val height = canvas.height

        for (i in 0..height step cellSize) {
            canvas.drawLine(0f, i.toFloat(), width.toFloat(), i.toFloat(), paintGrid)
        }

        for (i in 0..width step cellSize) {
            canvas.drawLine(i.toFloat(), 0f, i.toFloat(), height.toFloat(), paintGrid)
        }
    }
}