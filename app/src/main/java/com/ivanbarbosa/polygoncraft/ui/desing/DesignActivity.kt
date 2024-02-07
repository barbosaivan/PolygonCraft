package com.ivanbarbosa.polygoncraft.ui.desing

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityDesingBinding

class DesignActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesingBinding
    private lateinit var canvasView: CanvasDrawPolygon


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        canvasView = findViewById(R.id.canvasView)


        val polygon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("POLYGON", Polygon::class.java)
        } else {
            intent.getParcelableExtra<Polygon>("POLYGON")
        }

        if (polygon != null) {
            drawPolygon(polygon)
        }
    }

    private fun drawPolygon(polygon: Polygon) {
        canvasView.drawPolygon(polygon)
    }
}