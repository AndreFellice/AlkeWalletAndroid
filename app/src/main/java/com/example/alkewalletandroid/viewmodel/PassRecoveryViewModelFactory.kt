package com.example.alkewalletandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletandroid.model.database.AppDataBase

class PassRecoveryViewModelFactory(private val database: AppDataBase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PassRecoveryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PassRecoveryViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}