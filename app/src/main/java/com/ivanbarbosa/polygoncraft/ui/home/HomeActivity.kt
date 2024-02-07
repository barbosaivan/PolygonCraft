package com.ivanbarbosa.polygoncraft.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityHomeBinding
import com.ivanbarbosa.polygoncraft.ui.desing.DesignActivity
import com.ivanbarbosa.polygoncraft.ui.home.adapters.HomeAdapter
import com.ivanbarbosa.polygoncraft.ui.home.adapters.onClickListeners.OnClickListenerHome
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
        val myDialog = DialogCreatePolygon(this)
        myDialog.showDialog(this)
    }

    override fun onPositiveButtonClick(sides: Int, selectedScale: String) {
        // Positive Button
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