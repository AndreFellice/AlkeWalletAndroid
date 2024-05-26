package com.example.alkewalletandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Pantalla5HomeViewModel : ViewModel(){

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userPhotoUri =  MutableLiveData<String?>().apply {
        value = null // Inicialmente no hay foto de usuario
    }
    val userPhotoUri: LiveData<String?> = _userPhotoUri

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setUserPhotoUri(uri: String?) {
        _userPhotoUri.value = uri
    }
}
