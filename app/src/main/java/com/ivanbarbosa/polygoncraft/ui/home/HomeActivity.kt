package com.ivanbarbosa.polygoncraft.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ActivityHomeBinding
import com.ivanbarbosa.polygoncraft.ui.home.adapters.HomeAdapter
import com.ivanbarbosa.polygoncraft.ui.home.adapters.onClickListeners.OnClickListenerHome

class HomeActivity : AppCompatActivity(), OnClickListenerHome {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var gridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecycler()
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

    override fun onClick(polygon: Polygon) {
        //Click CardView
    }
}