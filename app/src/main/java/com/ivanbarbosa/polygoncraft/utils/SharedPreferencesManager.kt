package com.ivanbarbosa.polygoncraft.utils

import android.content.Context


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.utils
* Create by Ivan Barbosa on 8/02/2024 at 8:25 a. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/



object SharedPreferencesManager {
    private const val PREF_NAME = "polygons_prefs"
    private const val KEY_NAME = "name"

    fun saveName(context: Context, name: String?) {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }


    fun getName(context: Context): String? {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getString(KEY_NAME, "")
    }


    fun removeName(context: Context) {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }
}

