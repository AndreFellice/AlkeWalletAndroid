package com.example.alkewalletandroid.model

import android.app.Application
import android.net.Uri
import com.example.alkewalletandroid.R

class MainModel(application: Application) {

    val videoUri: Uri = Uri.parse("android.resource://${application.packageName}/${R.raw.video1}")
}