package com.example.alkewalletandroid

import android.app.Application
import com.example.alkewalletandroid.model.database.AppDataBase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicialización de la base de datos aquí
        AppDataBase.getInstance(this)
    }
}