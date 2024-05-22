package com.example.alkewalletandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alkewalletandroid.model.AppDataBase
import com.example.alkewalletandroid.model.UserRepository
import kotlinx.coroutines.launch

class Pantalla3LoginViewModel(application: Application) : AndroidViewModel(application) {

   private val userRepository: UserRepository = UserRepository(AppDataBase.getInstance(application).userDao())



    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    private val _loginError = MutableLiveData<Boolean>()
    val loginError: LiveData<Boolean>
        get() = _loginError

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailAndPassword(email, password)
            if (user != null) {
                // Autenticación exitosa, navegar a Pantalla5HomeActivity
                _navigateToMain.value = true

            } else {
                // Autenticación fallida, establecer el estado de error de inicio de sesión
                _loginError.value = true
            }
        }
    }

    fun doneNavigating() {
        _navigateToMain.value = false
    }
}