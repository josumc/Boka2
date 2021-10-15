package com.example.boka2

import android.content.Context
import android.content.SharedPreferences

class Paswd (context: Context) {
    val PREFS_NAME = "com.example.boka2.sharedpreferencespaswd"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var paswd: String
        //comprobamos el archivo de sahred preferences
        get() = prefs.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}