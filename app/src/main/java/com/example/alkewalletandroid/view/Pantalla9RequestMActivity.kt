package com.example.alkewalletandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.databinding.ActivityPantalla9RequestBinding

class Pantalla9RequestMActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla9RequestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        // Inflar el binding
        binding = ActivityPantalla9RequestBinding.inflate(layoutInflater)
        setContentView(binding.root)


     binding.arrow7.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Pantalla5HomeActivity::class.java)
            startActivity(intent)
        })
    }
}