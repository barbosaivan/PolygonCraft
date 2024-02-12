package com.ivanbarbosa.polygoncraft.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.ivanbarbosa.polygoncraft.databinding.DialogPolygonLayoutBinding


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.home
* Create by Ivan Barbosa on 7/02/2024 at 11:32 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class PolygonDialog(
    private val context: Context,
    private val requiredEditText: Boolean,
    private val requiredSpinner: Boolean,
    private val title: String,
    private val inputType: Int?,
    private val hintEditText: String?,
    private val descriptionSpinner: String?,
    private val optionsSpinner: Array<String>?
) {

    interface DialogCallback {
        fun onPositiveButtonClick(contentEditText: String?, selectedScale: String)
        fun onCancelButtonClick()
    }

    fun showDialog(callback: DialogCallback) {
        val binding = DialogPolygonLayoutBinding.inflate(LayoutInflater.from(context))

        binding.title.text = title
        if (inputType != null) {
            binding.etSides.inputType = inputType
        }
        binding.etSides.hint = hintEditText
        binding.tvSpinnerDescription.text = descriptionSpinner


        if (!requiredEditText) binding.etSides.visibility = View.GONE
        if (!requiredSpinner) {
            binding.spinnerScale.visibility = View.GONE
            binding.tvSpinnerDescription.visibility = View.GONE
        } else {
            val adapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_item, optionsSpinner!!)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerScale.adapter = adapter
        }

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(binding.root)
        val alertDialog = alertDialogBuilder.create()

        binding.btnPositive.setOnClickListener {
            val sides = if (binding.etSides.isVisible) binding.etSides.text.toString() else null
            val selectedScale =
                if (binding.spinnerScale.isVisible) binding.spinnerScale.selectedItem.toString() else ""
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