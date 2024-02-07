package com.ivanbarbosa.polygoncraft.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.databinding.ItemPolygonBinding
import com.ivanbarbosa.polygoncraft.ui.home.adapters.onClickListeners.OnClickListenerHome


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.home.adapters
* Create by Ivan Barbosa on 5/02/2024 at 7:24 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class HomeAdapter(
    private var polygons: MutableList<Polygon>,
    private val listener: OnClickListenerHome
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    constructor(
        context: Context,
        polygons: MutableList<Polygon>,
        listener: OnClickListenerHome
    ) : this(polygons, listener) {
        this.context = context
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_polygon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val polygon = polygons[position]
        holder.bind(polygon)
    }

    override fun getItemCount(): Int = polygons.size

    fun addPolygons(newPolygons: List<Polygon>) {
        val startingPosition = this.polygons.size
        this.polygons = newPolygons.toMutableList()
        notifyItemRangeInserted(startingPosition, newPolygons.size)
    }

    fun updatePolygon(updatedPolygon: Polygon) {
        val position = this.polygons.indexOf(updatedPolygon)
        this.polygons[position] = updatedPolygon
        notifyItemChanged(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPolygonBinding.bind(view)

        fun bind(polygon: Polygon) {
            binding.namePolygon.text = polygon.name
            binding.descriptionPolygon.text = polygon.points.size.toString()
            binding.root.setOnClickListener { listener.onClick(polygon) }
        }
    }
}
