package com.example.alkewalletandroid.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alkewalletandroid.model.Pantalla2WelcomeModel

class Pantalla2WelcomeViewModel(application: Application) : AndroidViewModel(application) {

    val model = Pantalla2WelcomeModel(application)
    private val _navigateToSignup = MutableLiveData<Boolean>()
    val navigateToSignup: LiveData<Boolean>
        get() = _navigateToSignup

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    init {
        _videoUri.value = model.videoUri
    }
    fun onSignupClicked(){
        _navigateToSignup.value = true
    }
    fun onLoginClicked() {
        _navigateToLogin.value = true
    }

    fun onLogoClicked() {
        _navigateToMain.value = true
    }

    fun doneNavigating() {
        _navigateToSignup.value = false
        _navigateToLogin.value = false
        _navigateToMain.value = false
    }
}