package com.ivanbarbosa.polygoncraft.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityHomeBinding
import com.ivanbarbosa.polygoncraft.ui.desing.DesignActivity
import com.ivanbarbosa.polygoncraft.ui.dialogs.PolygonDialog
import com.ivanbarbosa.polygoncraft.ui.home.adapters.HomeAdapter
import com.ivanbarbosa.polygoncraft.ui.home.adapters.onClickListeners.OnClickListenerHome
import com.ivanbarbosa.polygoncraft.utils.Constants
import com.ivanbarbosa.polygoncraft.utils.Scale
import com.ivanbarbosa.polygoncraft.utils.SharedPreferencesManager.getName
import com.ivanbarbosa.polygoncraft.utils.SharedPreferencesManager.removeName
import com.ivanbarbosa.polygoncraft.utils.calculateCoordinates
import com.ivanbarbosa.polygoncraft.utils.getStringArray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnClickListenerHome, PolygonDialog.DialogCallback {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var gridLayout: GridLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var polygon: Polygon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecycler()
        setUpViewModel()
        setUpButton()
        upSharedPreferences()
    }

    override fun onStart() {
        super.onStart()
        requestArtist()
    }

    private fun setUpRecycler() {
        homeAdapter = HomeAdapter(mutableListOf(), this@HomeActivity)
        gridLayout = GridLayoutManager(this, 1)
        binding.recyclerHome.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
            adapter = this@HomeActivity.homeAdapter
        }
    }

    private fun setUpViewModel() {
        viewModel.getResult().observe(this) { polygon ->
            homeAdapter.addPolygons(polygon)
            binding.progressbar.visibility = View.VISIBLE
        }
        viewModel.isLoaded().observe(this) { isLoaded ->
            binding.progressbar.visibility = if (!isLoaded) View.VISIBLE else View.GONE
        }
        viewModel.getSnackbarMsg().observe(this) { msgErr ->
            Snackbar.make(binding.root, msgErr, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setUpButton() {
        binding.btnCreatePolygon.setOnClickListener {
            showDialog(
                true,
                InputType.TYPE_CLASS_NUMBER,
                getString(R.string.title_dialog_create_polygon),
                getString(R.string.hint_et_sides)
            )
        }
    }

    private fun upSharedPreferences() {
        if (!getName(applicationContext).isNullOrEmpty()) {
            setUpObserverByName()
            viewModel.getPolygonByName(getName(applicationContext)!!)
        }
    }

    private fun requestArtist() {
        viewModel.getPolygons()
    }

    private fun showDialog(
        requiredEditText: Boolean,
        inputType: Int?,
        title: String,
        hintEditText: String?
    ) {
        val dialog = PolygonDialog(
            context = this,
            requiredEditText = requiredEditText,
            requiredSpinner = true,
            title = title,
            inputType = inputType,
            hintEditText = hintEditText,
            descriptionSpinner = getString(R.string.description_sniper_create_polygon),
            optionsSpinner = getStringArray(R.array.scale_options)
        )
        dialog.showDialog(this)
    }

    private fun setUpObserverByName() {
        viewModel.getSelectedPolygon().observe(this) {
            if (it != null) {
                removeName(applicationContext)
                startDesignActivity(it)
            }
        }
    }

    private fun startDesignActivity(polygon: Polygon) {
        val intent = Intent(this, DesignActivity::class.java)
        intent.putExtra(Constants.OBJECT_POLYGON, polygon)
        startActivity(intent)
    }

    override fun onPositiveButtonClick(contentEditText: String?, selectedScale: String) {
        if (contentEditText == null) {
            val scale = Scale.valueOf(selectedScale).value
            startDesignActivity(Polygon(polygon.name, polygon.points, scale))
        } else if (contentEditText.isEmpty() || contentEditText.toInt() <= 2) {
            Snackbar.make(
                binding.root,
                getString(R.string.message_invalid_number_of_sides), Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val scale = Scale.valueOf(selectedScale).value
            val pointsList = mutableListOf<Point>()
            pointsList.calculateCoordinates(contentEditText.toInt())
            startDesignActivity(Polygon("", pointsList, scale))
        }
    }

    override fun onCancelButtonClick() {
        // Negative Button
    }

    override fun onClick(polygon: Polygon) {
        this.polygon = polygon
        showDialog(
            false,
            null,
            getString(R.string.title_selected_scale),
            null
        )
    }
}