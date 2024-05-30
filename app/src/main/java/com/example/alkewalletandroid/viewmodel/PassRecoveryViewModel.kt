package com.example.alkewalletandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkewalletandroid.model.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PassRecoveryViewModel(private val database: AppDataBase) : ViewModel() {

    fun updateUserPassword(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val userDao = database.userDao()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = userDao.getUserByEmail(email)
                if (user != null) {
                    user.password = password
                    userDao.update(user)
                    withContext(Dispatchers.Main) {
                        onSuccess()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError("No se encontró ningún usuario con el correo electrónico proporcionado")
                    }
                }
            }
        }
    }
}