package com.ivanbarbosa.polygoncraft.ui.desing

import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityDesingBinding
import com.ivanbarbosa.polygoncraft.ui.desing.canvas.CanvasDrawPolygon
import com.ivanbarbosa.polygoncraft.ui.dialogs.PolygonDialog
import com.ivanbarbosa.polygoncraft.utils.Constants
import com.ivanbarbosa.polygoncraft.utils.SharedPreferencesManager.removeName
import com.ivanbarbosa.polygoncraft.utils.SharedPreferencesManager.saveName
import com.ivanbarbosa.polygoncraft.utils.calculateScalePoint
import com.ivanbarbosa.polygoncraft.utils.investScalePoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DesignActivity : AppCompatActivity(), PolygonDialog.DialogCallback {

    private lateinit var binding: ActivityDesingBinding
    private lateinit var canvasView: CanvasDrawPolygon
    private val viewModel: DesignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        canvasView = binding.canvasView
        init()
        setUpButton()
    }

    private fun init() {
        val polygon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.OBJECT_POLYGON, Polygon::class.java)
        } else {
            intent.getParcelableExtra(Constants.OBJECT_POLYGON)
        }

        if (polygon != null) {
            val pointsList = mutableListOf<Point>()
            pointsList.calculateScalePoint(polygon.points, polygon.scale)

            drawPolygon(Polygon(polygon.name, pointsList, polygon.scale))
        }
    }

    private fun setUpButton() {
        val fabSave: FloatingActionButton = binding.fabSave
        fabSave.setOnClickListener {
            showDialog()
        }
    }

    private fun setUpObserver() {
        viewModel.polygonSaved.observe(this) { saved ->
            if (saved) {
                Toast.makeText(
                    this,
                    getString(R.string.message_saved_polygon),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                showSnackbar(getString(R.string.message_error_saving_polygon))
                removeName(applicationContext)
            }
        }
    }

    private fun showDialog() {
        val dialog = PolygonDialog(
            context = this,
            requiredEditText = true,
            requiredSpinner = false,
            title = getString(R.string.save_polygon),
            inputType = InputType.TYPE_CLASS_TEXT,
            hintEditText = getString(R.string.text_polygon_name),
            descriptionSpinner = null,
            optionsSpinner = null
        )
        dialog.showDialog(this)
    }

    private fun drawPolygon(polygon: Polygon) {
        canvasView.drawPolygon(polygon)
    }

    override fun onPositiveButtonClick(contentEditText: String?, selectedScale: String) {
        if (contentEditText.isNullOrEmpty()) {
            showSnackbar(getString(R.string.message_name_polygon_empty))
            return
        }

        val polygon = canvasView.getPolygon()
        val pointsList = mutableListOf<Point>()
        if (polygon != null) {
            pointsList.investScalePoint(polygon.points, polygon.scale)
            viewModel.savePolygon(Polygon(contentEditText, pointsList, polygon.scale))
            setUpObserver()
            removeName(applicationContext)
            saveName(applicationContext, contentEditText)
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