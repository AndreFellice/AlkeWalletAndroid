package com.example.alkewalletandroid.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla7ProfileBinding

class Pantalla7ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla7ProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        // Inflar el binding
        binding = ActivityPantalla7ProfileBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        // Establecer la ruta del video y comenzar el video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.video3
        binding.videoView3.setVideoURI(Uri.parse(videoPath))
        binding.videoView3.start()

        // Manejar los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Establecer OnClickListener para el botón de inicio
        binding.scarlett.setOnClickListener {
            val intent = Intent(this@Pantalla7ProfileActivity, Pantalla5HomeActivity::class.java)
            startActivity(intent)
        }
    }
}