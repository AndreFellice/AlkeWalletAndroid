package com.example.alkewalletandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.databinding.ActivityPantalla5HomeBinding

class Pantalla5HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla5HomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityPantalla5HomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        binding.btn8.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla8SendMActivivity::class.java)
            startActivity(intent)
        })

        binding.btn9.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla9RequestMActivity::class.java)
            startActivity(intent)
        })

        binding.campana.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla6EmptyActivity::class.java)
            startActivity(intent)
        })

        binding.fotoCliente2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla7ProfileActivity::class.java)
            startActivity(intent)
        })


        binding.logo5.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        })
    }
}