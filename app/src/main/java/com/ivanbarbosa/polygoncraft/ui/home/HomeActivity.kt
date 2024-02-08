package com.ivanbarbosa.polygoncraft.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityHomeBinding
import com.ivanbarbosa.polygoncraft.ui.desing.DesignActivity
import com.ivanbarbosa.polygoncraft.ui.home.adapters.HomeAdapter
import com.ivanbarbosa.polygoncraft.ui.home.adapters.onClickListeners.OnClickListenerHome
import com.ivanbarbosa.polygoncraft.utils.HomeUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnClickListenerHome, DialogCreatePolygon.DialogCallback {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var gridLayout: GridLayoutManager
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecycler()
        setUpViewModel()
        setUpButton()
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
            showDialog()
        }
    }

    private fun requestArtist() {
        viewModel.getPolygons()
    }

    private fun showDialog() {
        val dialog = DialogCreatePolygon(this)
        dialog.showDialog(this)
    }

    override fun onPositiveButtonClick(sides: Int, selectedScale: String) {
        if (sides <= 2) {
            Snackbar.make(binding.root,
                getString(R.string.message_invalid_number_of_sides), Snackbar.LENGTH_SHORT)
                .show()
        } else {
            val scale = when (selectedScale) {
                "x1" -> 0.33
                "x2" -> 0.5
                "x3" -> 1.0
                else -> 1.0
            }
            val points = HomeUtils.calculateCoordinates(sides, scale, scale)
            val polygon = Polygon("", points)
            val intent = Intent(this, DesignActivity::class.java)
            intent.putExtra("POLYGON", polygon)
            startActivity(intent)
        }
    }

    override fun onCancelButtonClick() {
        // Negative Button
    }

    override fun onClick(polygon: Polygon) {
        val intent = Intent(this, DesignActivity::class.java)
        intent.putExtra("POLYGON", polygon)
        startActivity(intent)
    }
}