package com.example.alkewalletandroid.viewmodel


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alkewalletandroid.model.MainModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val model = MainModel(application)

    private val _navigateToWelcomeScreen = MutableLiveData<Boolean>()
    val navigateToWelcomeScreen: LiveData<Boolean>
        get() = _navigateToWelcomeScreen

    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    init {
        _videoUri.value = model.videoUri
    }

    fun onLogoClicked() {
        _navigateToWelcomeScreen.value = true
    }

    fun onVideoCompleted() {
        _navigateToWelcomeScreen.value = true
    }

    fun doneNavigating() {
        _navigateToWelcomeScreen.value = false
    }
}