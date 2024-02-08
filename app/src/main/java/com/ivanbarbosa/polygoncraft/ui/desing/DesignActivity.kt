package com.ivanbarbosa.polygoncraft.ui.desing

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityDesingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DesignActivity : AppCompatActivity(), DialogSavePolygon.DialogCallback {

    private lateinit var binding: ActivityDesingBinding
    private lateinit var canvasView: CanvasDrawPolygon
    private val viewModel: DesignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        canvasView = findViewById(R.id.canvasView)
        init()
        setUpButton()
    }

    private fun init() {
        val polygon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("POLYGON", Polygon::class.java)
        } else {
            intent.getParcelableExtra<Polygon>("POLYGON")
        }

        if (polygon != null) {
            drawPolygon(polygon)
        }
    }

    private fun setUpButton() {
        val fabSave: FloatingActionButton = findViewById(R.id.fabSave)
        fabSave.setOnClickListener {
            showDialog()
        }
    }

    private fun setUpObserver() {
        viewModel.polygonSaved.observe(this, Observer { saved ->
            if (saved) {
                showSnackbar(getString(R.string.message_saved_polygon))
                finish()
            } else {
                showSnackbar(getString(R.string.message_error_saving_polygon))
            }
        })
    }

    private fun showDialog() {
        val dialog = DialogSavePolygon(this)
        dialog.showDialog(this)
    }

    private fun drawPolygon(polygon: Polygon) {
        canvasView.drawPolygon(polygon)
    }

    override fun onPositiveButtonClick(name: String) {
        if (name.isBlank()) {
            showSnackbar(getString(R.string.message_name_polygon_empty))
            return
        }

        val polygon = canvasView.getPolygon()
        if (polygon != null) {
            viewModel.savePolygon(Polygon(name, polygon.points))
            setUpObserver()
        } else {
            showSnackbar(getString(R.string.message_polygon_empty))
        }
    }

    override fun onCancelButtonClick() {
        // NegativeButton
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}