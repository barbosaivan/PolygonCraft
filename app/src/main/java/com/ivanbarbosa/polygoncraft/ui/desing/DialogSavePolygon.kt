package com.ivanbarbosa.polygoncraft.ui.desing

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.ivanbarbosa.polygoncraft.R


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 8/02/2024 at 2:20 a. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class DialogSavePolygon(private val context: Context) {

    interface DialogCallback {
        fun onPositiveButtonClick(name: String)
        fun onCancelButtonClick()
    }

    fun showDialog(callback: DialogCallback) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_save_layout, null)

        val etSides = dialogView.findViewById<EditText>(R.id.etSides)


        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()

        val btnPositive = dialogView.findViewById<Button>(R.id.btnPositive)
        val btnNegative = dialogView.findViewById<Button>(R.id.btnNegative)

        btnPositive.setOnClickListener {
            val name = etSides.text.toString() ?: ""
            callback.onPositiveButtonClick(name)
            alertDialog.dismiss()
        }

        btnNegative.setOnClickListener {
            callback.onCancelButtonClick()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}