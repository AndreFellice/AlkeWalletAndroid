package com.example.alkewalletandroid.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla7ProfileBinding
import com.example.alkewalletandroid.view.fragments.CentroDeAyudaFragment
import com.example.alkewalletandroid.view.fragments.MiInformacionFragment
import com.example.alkewalletandroid.view.fragments.MisAjustesFragment
import com.example.alkewalletandroid.view.fragments.MisTarjetasFragment

class Pantalla7ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPantalla7ProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla7ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar el nombre y la foto del usuario
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        binding.textv12.text = sharedPreferences.getString("name", "Nombre de Usuario")
        val imageUri = sharedPreferences.getString("profile_image_uri", null)
        if (imageUri != null) {
            binding.scarlett.setImageURI(Uri.parse(imageUri))
        }

        // Establecer OnClickListener para los botones
        binding.scarlett.setOnClickListener {
            val intent = Intent(this, Pantalla5HomeActivity::class.java)
            startActivity(intent)
        }

        binding.arrow1.setOnClickListener {
            showFragment(MiInformacionFragment())
        }

        binding.arrow2.setOnClickListener {
            showFragment(MisTarjetasFragment())
        }

        binding.arrow3.setOnClickListener {
            showFragment(MisAjustesFragment())
        }

        binding.arrow4.setOnClickListener {
            showFragment(CentroDeAyudaFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}