package com.example.alkewalletandroid.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.databinding.ActivityPantalla8SendMoneyBinding

class Pantalla8SendMActivivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla8SendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        // Inflar el binding
        binding = ActivityPantalla8SendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Establecer OnClickListener para el botón de atrás
        binding.arrow5.setOnClickListener {
            val intent = Intent(this@Pantalla8SendMActivivity, Pantalla5HomeActivity::class.java)
            startActivity(intent)
        }
    }
}