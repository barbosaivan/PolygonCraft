package com.ivanbarbosa.polygoncraft.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.databinding.DialogCreateLayoutBinding


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.home
* Create by Ivan Barbosa on 7/02/2024 at 11:32 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class DialogCreatePolygon(private val context: Context) {

    interface DialogCallback {
        fun onPositiveButtonClick(sides: Int, selectedScale: String)
        fun onCancelButtonClick()
    }

    fun showDialog(callback: DialogCallback) {
        val binding = DialogCreateLayoutBinding.inflate(LayoutInflater.from(context))

        val scaleOptions = context.resources.getStringArray(R.array.scale_options)
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, scaleOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(binding.root)

        val alertDialog = alertDialogBuilder.create()

        binding.btnPositive.setOnClickListener {
            val sides = binding.etSides.text.toString().toIntOrNull() ?: 0
            val selectedScale = binding.spinnerScale.selectedItem.toString()
            callback.onPositiveButtonClick(sides, selectedScale)
            alertDialog.dismiss()
        }

        binding.btnNegative.setOnClickListener {
            callback.onCancelButtonClick()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}