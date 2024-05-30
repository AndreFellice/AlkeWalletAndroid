package com.example.alkewalletandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alkewalletandroid.model.database.AppDataBase
import com.example.alkewalletandroid.model.entities.User
import com.example.alkewalletandroid.model.repository.UserRepository
import kotlinx.coroutines.launch

class Pantalla4SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(AppDataBase.getInstance(application).userDao())

    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess: LiveData<Boolean>
        get() = _signupSuccess

    private val _signupError = MutableLiveData<String>()
    val signupError: LiveData<String>
        get() = _signupError

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        photoUri : String
    ) {
        viewModelScope.launch {
            try {
                val existingUser = userRepository.getUserByEmail(email)
                if (existingUser != null) {
                    _signupError.value = "El correo ya está registrado"
                } else {
                    val user = User(nombres = firstName, apellidos = lastName, email = email, password = password, photoUri = photoUri )
                    userRepository.insertUser(user)
                    _signupSuccess.value = true
                }
            } catch (e: Exception) {
                _signupError.value = "Error al registrar el usuario: ${e.message}"
            }
        }
    }

}
