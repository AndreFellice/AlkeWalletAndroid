package com.example.alkewalletandroid.view.fragments


import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.FragmentPassRecoveryBinding
import com.example.alkewalletandroid.model.database.AppDataBase
import com.example.alkewalletandroid.view.activities.Pantalla3LoginActivity
import com.example.alkewalletandroid.viewmodel.PassRecoveryViewModel
import com.example.alkewalletandroid.viewmodel.PassRecoveryViewModelFactory


class PassRecoveryFragment : Fragment() {


    private var _binding: FragmentPassRecoveryBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false
    private var isRPasswordVisible = false

    private val viewModel: PassRecoveryViewModel by viewModels {
        PassRecoveryViewModelFactory(AppDataBase.getInstance(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflando el diseño para este fragmento usando ViewBinding
        _binding = FragmentPassRecoveryBinding.inflate(inflater, container, false)

        setupViews()

        // Establecer OnClickListener para recoveryButton
        binding.btn12.setOnClickListener {
            // Obtener el correo electrónico ingresado por el usuario
            val email = binding.emailUsuario.text.toString()
            val password = binding.edt.text.toString()
            val confirmPassword = binding.edt2.text.toString()

            // metodo que realiza la lógica de recuperación y actualización de la contraseña
         if (validatePassword(password, confirmPassword)) {
                updateUserPassword(email, password)
            } else {
             showToast("Las contraseñas no coinciden")
            }
        }


        return binding.root
    }
    private fun setupViews() {
        binding.ojo4.setOnClickListener { togglePasswordVisibility() }
        binding.ojo5.setOnClickListener { toggleRPasswordVisibility() }
    }

    private fun validatePassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && password.length == 6
    }

    private fun updateUserPassword(email: String, password: String) {
         // Actualiza la contraseña en la base de datos para el correo electrónico ingresado a traves de viewmodel
        viewModel.updateUserPassword(email, password,
            onSuccess = {
                showToast("Contraseña actualizada correctamente")
                navigateToLoginActivity()
            },
            onError = { message ->
                showToast(message)
            }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun togglePasswordVisibility() {
        isPasswordVisible   = !isPasswordVisible
        val drawableId = if (isPasswordVisible) R.drawable.visibility_off else R.drawable.visibility
        binding.ojo4.setImageResource(drawableId)

        val inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.edt.inputType = inputType
        // Establecer el cursor al final del texto en los EditTexts
        binding.edt.setSelection(binding.edt.text.length)
    }


    private fun toggleRPasswordVisibility() {
        isRPasswordVisible= !isRPasswordVisible
        val drawableId = if (isRPasswordVisible) R.drawable.visibility_off else R.drawable.visibility
        binding.ojo5.setImageResource(drawableId)

        val inputType = if (isRPasswordVisible) {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.edt2.inputType = inputType
        // Establecer el cursor al final del texto en los EditTexts
        binding.edt2.setSelection(binding.edt2.text.length)
    }
    private fun navigateToLoginActivity() {
        // volver a Pantalla Login de usuario
        val intent = Intent(requireContext(), Pantalla3LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish() //
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}