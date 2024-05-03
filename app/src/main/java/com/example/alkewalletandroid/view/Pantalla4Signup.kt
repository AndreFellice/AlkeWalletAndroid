package com.example.alkewalletandroid.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla4SignupBinding
import com.example.alkewalletandroid.viewmodel.UserViewModel


class Pantalla4Signup : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla4SignupBinding
    private var isPasswordVisible = false
    private var isRPasswordVisible = false
        private val viewModel: UserViewModel by viewModels()
        companion object {
            private const val REQUEST_IMAGE_CAPTURE = 1
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla4SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ojo2.setOnClickListener { togglePasswordVisibility() }
            ojo3.setOnClickListener { toggleRPasswordVisibility() }
            btn6.setOnClickListener { registerUser() }
            btn7.setOnClickListener { navigateToLogin() }
            logo4.setOnClickListener { navigateToMainActivity() }
            btnTomarFoto.setOnClickListener { takePhoto() }
        }
    }
 private  fun navigateToMainActivity(){
     val intent = Intent(this, Pantalla2Welcome::class.java)
     startActivity(intent)
 }
    private fun togglePasswordVisibility() {
        isPasswordVisible   = !isPasswordVisible
        val drawableId = if (isPasswordVisible) R.drawable.visibility_off else R.drawable.visibility
        binding.ojo2.setImageResource(drawableId)

        val inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.Password2.inputType = inputType
        // Establecer el cursor al final del texto en los EditTexts
        binding.Password2.setSelection(binding.Password2.text.length)
    }


    private fun toggleRPasswordVisibility() {
       isRPasswordVisible= !isRPasswordVisible
        val drawableId = if (isRPasswordVisible) R.drawable.visibility_off else R.drawable.visibility
        binding.ojo3.setImageResource(drawableId)

        val inputType = if (isRPasswordVisible) {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.RPassword.inputType = inputType
        // Establecer el cursor al final del texto en los EditTexts
        binding.RPassword.setSelection(binding.RPassword.text.length)
    }

    private fun registerUser() {
        val firstName = binding.nombres.text.trim().toString()
        val lastName = binding.apellidos.text.trim().toString()
        val userEmail = binding.Email2.text.trim().toString()
        val userPassword = binding.Password2.text.trim().toString()
        val confirmPassword = binding.RPassword.text.trim().toString()

        if (firstName.isEmpty() || lastName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        if (userPassword != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }
      //  userViewModel.registerUser(firstName, lastName, userEmail, userPassword)

    }

    @Suppress("DEPRECATION")
    @SuppressLint("QueryPermissionsNeeded")
    private fun takePhoto() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePhotoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data?.extras
            val imageBitmap = extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                binding.perfilImageView.setImageBitmap(imageBitmap)
            } else {
                Toast.makeText(this, "Error al obtener la foto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@Pantalla4Signup, Pantalla3Login::class.java)
        startActivity(intent)

    }

}
