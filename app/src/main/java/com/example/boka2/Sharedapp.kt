package com.example.boka2

import android.app.Application

class Sharedapp : Application() {
    companion object {
        lateinit var prefs: Prefs
        lateinit var user: User
        lateinit var paswd: Paswd
        lateinit var tipo: tipo
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        user = User(applicationContext)
        paswd = Paswd(applicationContext)
        tipo = tipo(applicationContext)

    }

}
