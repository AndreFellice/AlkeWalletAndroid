package com.example.alkewalletandroid.view

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla4SignupBinding
import com.example.alkewalletandroid.viewmodel.Pantalla4SignupViewModel
import java.io.IOException
import java.io.OutputStream


class Pantalla4SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla4SignupBinding
    private var isPasswordVisible = false
    private var isRPasswordVisible = false
    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
    private lateinit var userPhotoUri: Uri // Variable para almacenar la URI de la foto
    private val viewModel: Pantalla4SignupViewModel by viewModels()
        companion object {

            private const val CAMERA_PERMISSION_CODE = 100
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityPantalla4SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        takePictureLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    val extras = data?.extras
                    val imageBitmap = extras?.get("data") as? Bitmap
                    if (imageBitmap != null) {
                        // Guardar la imagen y obtener su URI
                        userPhotoUri = saveImageAndGetUri(imageBitmap)
                        binding.perfilImageView.setImageBitmap(imageBitmap)
                    } else {
                        Toast.makeText(this, "Error al obtener la foto", Toast.LENGTH_SHORT).show()
                    }
                }
            }



        binding.apply {
            ojo2.setOnClickListener { togglePasswordVisibility() }
            ojo3.setOnClickListener { toggleRPasswordVisibility() }
            btn6.setOnClickListener { registerUser() }
            btn7.setOnClickListener { navigateToLogin() }
            logo4.setOnClickListener { navigateToMainActivity() }
            btnTomarFoto.setOnClickListener { takePhoto() }
        }
        viewModel.signupSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                navigateToLogin()
            }
        }

        viewModel.signupError.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
    // Función para guardar la imagen en la galería y obtener su URI
    private fun saveImageAndGetUri(imageBitmap: Bitmap): Uri {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DISPLAY_NAME, "user_photo_${System.currentTimeMillis()}.jpg")
        }
        val savedImageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (savedImageUri != null) {
            var outputStream: OutputStream? = null
            try {
                outputStream = contentResolver.openOutputStream(savedImageUri)
                if (outputStream != null) {
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                outputStream?.close()
            }
        }
        return savedImageUri ?: Uri.EMPTY
    }
    private fun navigateToHome() {
        // Pasa la URI de la foto a la actividad de inicio
        val intent = Intent(this,Pantalla5HomeActivity::class.java).apply {
            putExtra("user_photo_uri", userPhotoUri.toString())
        }
        startActivity(intent)

    }
 private  fun navigateToMainActivity(){
     val intent = Intent(this, Pantalla2WelcomeActivity::class.java)
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

        if (userPhotoUri == null) {
            Toast.makeText(this, "Por favor, toma una foto de perfil", Toast.LENGTH_SHORT).show()
            return
        }


     viewModel.registerUser(firstName, lastName, userEmail, userPassword, userPhotoUri.toString())

    }

    private fun takePhoto() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // El permiso no ha sido concedido, se solicita al usuario junto con una explicación
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                // Mostrar explicación al usuario antes de solicitar el permiso
                Toast.makeText(
                    this,
                    "Se necesita el permiso de cámara para tomar una foto de perfil.",
                    Toast.LENGTH_LONG
                ).show()
            }
            // Solicitar el permiso
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            // El permiso ya ha sido concedido, abrir la cámara
            openCamera()
        }
    }
        private fun openCamera() {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePhotoIntent.resolveActivity(packageManager) != null) {
                takePictureLauncher.launch(takePhotoIntent)
            }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, open the camera
                openCamera()
            } else {
                // Permission denied
                Toast.makeText(this, "Permiso para usar la cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@Pantalla4SignupActivity, Pantalla3LoginActivity::class.java)
        startActivity(intent)

    }


}
