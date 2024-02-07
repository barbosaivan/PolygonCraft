package com.ivanbarbosa.polygoncraft.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.ivanbarbosa.polygoncraft.R


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
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_layout, null)

        val etSides = dialogView.findViewById<EditText>(R.id.etSides)
        val spinnerScale = dialogView.findViewById<Spinner>(R.id.spinnerScale)

        val scaleOptions = context.resources.getStringArray(R.array.scale_options)
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, scaleOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()

        val btnPositive = dialogView.findViewById<Button>(R.id.btnPositive)
        val btnNegative = dialogView.findViewById<Button>(R.id.btnNegative)

        btnPositive.setOnClickListener {
            val sides = etSides.text.toString().toIntOrNull() ?: 0
            val selectedScale = spinnerScale.selectedItem.toString()
            callback.onPositiveButtonClick(sides, selectedScale)
            alertDialog.dismiss()
        }

        btnNegative.setOnClickListener {
            callback.onCancelButtonClick()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}