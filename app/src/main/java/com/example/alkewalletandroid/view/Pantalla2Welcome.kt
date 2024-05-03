package com.example.alkewalletandroid.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla2WelcomeBinding

class Pantalla2Welcome : AppCompatActivity() {

    private lateinit var binding: ActivityPantalla2WelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla2WelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.enableEdgeToEdge()

        val videoView: VideoView = binding.videoView2

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.video2
        videoView.setVideoURI(Uri.parse(videoPath))

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()


        binding.btn1.setOnClickListener {
            val intent = Intent(this@Pantalla2Welcome, Pantalla4Signup::class.java)
            startActivity(intent)
        }

        binding.btn2.setOnClickListener {
            val intent = Intent(this@Pantalla2Welcome, Pantalla3Login::class.java)
            startActivity(intent)
        }

        binding.logo2.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}