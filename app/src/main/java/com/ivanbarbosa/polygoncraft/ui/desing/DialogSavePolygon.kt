package com.ivanbarbosa.polygoncraft.ui.desing

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.ivanbarbosa.polygoncraft.databinding.DialogCreateLayoutBinding


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 8/02/2024 at 2:20 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class DialogSavePolygon(private val context: Context) {

    interface DialogCallback {
        fun onPositiveButtonClick(name: String)
        fun onCancelButtonClick()
    }

    fun showDialog(callback: DialogCallback) {
        val binding = DialogCreateLayoutBinding.inflate(LayoutInflater.from(context))

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(binding.root)

        val alertDialog = alertDialogBuilder.create()

        binding.btnPositive.setOnClickListener {
            val name = binding.etSides.text.toString()
            callback.onPositiveButtonClick(name)
            alertDialog.dismiss()
        }

        binding.btnNegative.setOnClickListener {
            callback.onCancelButtonClick()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}