package com.example.alkewalletandroid.view

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alkewalletandroid.PassRecoveryFragment
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla3LoginBinding

class Pantalla3Login : AppCompatActivity() {

    private lateinit var binding: ActivityPantalla3LoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPantalla3LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ojo.setOnClickListener { togglePasswordVisibility() }
            btn3.setOnClickListener { loginUser() }
            btn4.setOnClickListener { navigateToSignUp() }
            btn5.setOnClickListener { showPasswordRecoveryFragment() }
            logo3.setOnClickListener { navigateToMainActivity() }
        }
    }

    private fun loginUser() {
        val email = binding.Email.text.toString()
        val password = binding.Password.text.toString()
        // Validación de campos de entrada
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor, ingresa tu correo electrónico y contraseña",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Aquí deberías agregar la lógica para verificar el usuario y la contraseña
    }
    // metodo para acceder a pantalla de registro de nuevo cliente
    private fun navigateToSignUp() {
        val intent = Intent(this, Pantalla4Signup::class.java)
        startActivity(intent)
    }
    // metodo para acceder a pantalla Welcome
 private fun navigateToMainActivity(){
     val intent = Intent(this, Pantalla2Welcome::class.java)
     startActivity(intent)
 }

    // Método para mostrar el fragmento de recuperación de contraseña
    private fun showPasswordRecoveryFragment() {
        // Crear una instancia del fragmento de recuperación de contraseña

        val recoveryFragment = PassRecoveryFragment()

        // Obtener el FragmentManager y comenzar una transacción para agregar el fragmento
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        // Reemplazar el contenedor actual con el fragmento de recuperación de contraseña
        transaction.replace(R.id.fragment_container, recoveryFragment)

        // Agregar la transacción a la pila de retroceso (opcional)
        transaction.addToBackStack(null)

        // Confirmar la transacción
        transaction.commit()
    }

     // metodo para mostrar o ocultar la contraseña
    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        val drawableId = if (isPasswordVisible) R.drawable.visibility_off else R.drawable.visibility
        binding.ojo.setImageResource(drawableId)

        val inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.Password.inputType = inputType
        // Establecer el cursor al final del texto en los EditTexts
        binding.Password.setSelection(binding.Password.text.length)
    }

}
