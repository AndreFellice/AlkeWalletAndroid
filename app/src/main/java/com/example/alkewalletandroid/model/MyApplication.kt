package com.example.alkewalletandroid.model

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicialización de la base de datos aquí
        AppDataBase.getInstance(this)
    }
}