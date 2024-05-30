package com.example.alkewalletandroid.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.databinding.ActivityPantalla6EmptyBinding

class Pantalla6EmptyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla6EmptyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        // Inflate the binding
        binding = ActivityPantalla6EmptyBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        // Using binding to access views
        binding.btn10.setOnClickListener {
            val intent = Intent(this@Pantalla6EmptyActivity, Pantalla8SendMActivivity::class.java)
            startActivity(intent)
        }

        binding.btn11.setOnClickListener {
            val intent = Intent(this@Pantalla6EmptyActivity, Pantalla9RequestMActivity::class.java)
            startActivity(intent)
        }

        binding.fotoCliente.setOnClickListener {
            val intent = Intent(this@Pantalla6EmptyActivity, Pantalla7ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.logo6.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}