package com.example.alkewalletandroid.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.enableEdgeToEdge()
        setContentView(binding.root)
        val videoView: VideoView = binding.videoView

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.video1
        videoView.setVideoURI(Uri.parse(videoPath))

        // Deshabilitar los controles del VideoView
        videoView.setMediaController(null)


        // Se agrega un Listener para detectar el final del video y así pasar a pantalla 2 automáticamente
        videoView.setOnCompletionListener {
            val intent = Intent(this@MainActivity, Pantalla2Welcome::class.java)
            startActivity(intent)
        }

        videoView.start()

        val next: ImageView = binding.logo



        next.setOnClickListener {
            val intent = Intent(this@MainActivity, Pantalla2Welcome::class.java)
            startActivity(intent)
        }
    }
}