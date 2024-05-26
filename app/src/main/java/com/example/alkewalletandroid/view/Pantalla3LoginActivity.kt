package com.example.alkewalletandroid.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla3LoginBinding
import com.example.alkewalletandroid.model.AppDataBase
import com.example.alkewalletandroid.model.User
import com.example.alkewalletandroid.view.fragments.PassRecoveryFragment
import com.example.alkewalletandroid.viewmodel.Pantalla3LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Pantalla3LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPantalla3LoginBinding
    private val viewModel: Pantalla3LoginViewModel by viewModels()
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPantalla3LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



       // Configurar el manejo del botón de retroceso
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ocultar el teclado si está visible
                hideKeyboard()
                // Si deseas terminar la actividad después de ocultar el teclado
                if (isEnabled) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        // Insertar usuario de ejemplo al iniciar la aplicación
        insertarUsuarioFellice()

        binding.apply {
            ojo.setOnClickListener { togglePasswordVisibility() }
            btn3.setOnClickListener { loginUser() }
            btn4.setOnClickListener { navigateToSignUp() }
            btn5.setOnClickListener { showPasswordRecoveryFragment() }
            logo3.setOnClickListener { navigateToMainActivity() }
        }
        observeViewModel()
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)
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
        viewModel.login(email, password)

    }
    // Método para insertar un usuario de ejemplo en la base de datos
    private fun insertarUsuarioFellice() {
        lifecycleScope.launch (Dispatchers.IO){
            val userDao = AppDataBase.getInstance(applicationContext).userDao()
            val photoUri = Uri.parse("android.resource://${packageName}/drawable/fellice")
            val usuarioEjemplo = User(
                id = 0, // Generar ID automáticamente
                nombres = "Andre Fellice ",
                apellidos = "Cuetos Picon",
                email = "andreeecp@gmail.com",
                password = "666999",
                photoUri = photoUri.toString()
            )
            if (userDao.getUserByEmail("andreeecp@gmail.com") == null) {
                userDao.insert(usuarioEjemplo)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToMain.observe(this, Observer { navigate ->
            if (navigate) {
                val intent = Intent(this, Pantalla5HomeActivity::class.java)
                startActivity(intent)
                viewModel.doneNavigating()
            }
        })

        viewModel.loginError.observe(this, Observer { isError ->
            if (isError) {
                Toast.makeText(
                    this,
                    "Correo electrónico o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    // metodo para acceder a pantalla de registro de nuevo cliente
    private fun navigateToSignUp() {
        val intent = Intent(this, Pantalla4SignupActivity::class.java)
        startActivity(intent)
    }
    // metodo para acceder a pantalla Welcome
 private fun navigateToMainActivity(){
     val intent = Intent(this, Pantalla2WelcomeActivity::class.java)
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

        // Agregar la transacción a la pila de retroceso
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
