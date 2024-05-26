package com.example.alkewalletandroid.viewmodel

import android.app.Application
import android.database.SQLException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alkewalletandroid.model.AppDataBase
import com.example.alkewalletandroid.model.User
import com.example.alkewalletandroid.model.UserRepository
import kotlinx.coroutines.launch

// ViewModel para manejar la lógica de usuario
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository // Repositorio para interactuar con la base de datos

    // LiveData para observar la lista de todos los usuarios
    val allUsers: LiveData<List<User>>

    // LiveData para notificar errores a la UI
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        // Inicialización del repositorio y pretencion del DAO de usuario
        val userDao = AppDataBase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers // Inicialización del LiveData con la lista de usuarios del repositorio
    }

    // Método para insertar un nuevo usuario
    fun addUser(user: User) {
        if (isValidPassword(user.password ?: "")) {
            viewModelScope.launch {
                val result = repository.insertUser(user)
                result.onFailure { exception ->
                    _error.value = handleDatabaseError(exception)
                }
            }
        } else {
            _error.value = "La contraseña debe ser un número de 6 dígitos."
        }
    }

    // Método para actualizar un usuario existente
    fun updateUser(user: User) {
        if (isValidPassword(user.password ?: "")) {
            viewModelScope.launch {
                val result = repository.updateUser(user)
                result.onFailure { exception ->
                    _error.value = handleDatabaseError(exception)
                }
            }
        } else {
            _error.value = "La contraseña debe ser un número de 6 dígitos."
        }
    }

    // Método para eliminar un usuario existente
    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            val result = repository.deleteUser(userId)
            result.onFailure { exception ->
                _error.value = handleDatabaseError(exception)
            }
        }
    }

    // Método para obtener un usuario por su ID
    fun getUserById(userId: Int): LiveData<User> {
        return repository.getUserById(userId)
    }

    // Método para obtener un usuario por su email
    fun getUserByEmail(email: String): MutableLiveData<User?> {
        val resultLiveData = MutableLiveData<User?>()
        viewModelScope.launch {
            try {
                val user = repository.getUserByEmail(email)
                resultLiveData.postValue(user)
            } catch (exception: Exception) {
                _error.value = "Error al obtener el usuario: ${exception.message}"
            }
        }
        return resultLiveData
    }

    // Método para obtener un usuario por su email y contraseña
    fun getUserByEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.getUserByEmailAndPassword(email, password)
            result.onFailure { exception ->
                _error.value = handleDatabaseError(exception)
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length == 6 && password.all { it.isDigit() }
    }

    private fun handleDatabaseError(exception: Throwable): String {
        return when (exception) {
            is SQLException -> "Error de base de datos: ${exception.message}"
            else -> "Error inesperado: ${exception.message}"
        }
    }
}