package com.example.alkewalletandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla5HomeBinding
import com.example.alkewalletandroid.viewmodel.Pantalla5HomeViewModel


class Pantalla5HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla5HomeBinding
    private val pantalla5HomeViewModel: Pantalla5HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityPantalla5HomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

            // Observa los cambios en los datos del usuario
            pantalla5HomeViewModel.userName.observe(this, Observer { name ->
                binding.textv30.text = name
            })
        // Observa los cambios en la URI de la foto del usuario
        pantalla5HomeViewModel.userPhotoUri.observe(this, Observer { uri ->
            if (uri != null) {
                Glide.with(this).load(uri).into(binding.fotoCliente2)
            } else {
                binding.fotoCliente2.setImageResource(R.drawable.usuario_nn)
            }
        })

        // Obtén la URI de la foto del intent y actualiza el ViewModel
        val userPhotoUriString = intent.getStringExtra("user_photo_uri")
        if (userPhotoUriString != null) {
            pantalla5HomeViewModel.setUserPhotoUri(userPhotoUriString)
        } else {
            // Establecer una foto de ejemplo si no hay URI de la imagen
            pantalla5HomeViewModel.setUserPhotoUri(null)
        }


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


        binding.logo5.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        })

        binding.btnPerfil.setOnClickListener(View.OnClickListener {
            val intent = Intent (this@Pantalla5HomeActivity, Pantalla7ProfileActivity::class.java)
            startActivity(intent)
        })
    }

}

