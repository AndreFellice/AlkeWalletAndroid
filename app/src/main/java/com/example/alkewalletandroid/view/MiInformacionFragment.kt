package com.example.alkewalletandroid.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.alkewalletandroid.databinding.FragmentMiInformacionBinding

class MiInformacionFragment : Fragment() {

    private var _binding: FragmentMiInformacionBinding? = null
    private val binding get() = _binding!!
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    companion object {
        const val PICK_IMAGE = 1

    }

    //private val viewModel: MiInformacionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
_binding = FragmentMiInformacionBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el ActivityResultLauncher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val imageUri = result.data?.data
                binding.profileImageView.setImageURI(imageUri)

                // Guardar la URI de la imagen en SharedPreferences
                val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("profile_image_uri", imageUri.toString())
                editor?.apply()
            }
        }

    // Cargar la información del usuario (esto puede venir de un ViewModel o base de datos)
    val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    binding.nameEditText.setText(sharedPreferences?.getString("name", ""))
    binding.rutEditText.setText(sharedPreferences?.getString("rut", ""))
    binding.addressEditText.setText(sharedPreferences?.getString("address", ""))
    binding.emailEditText.setText(sharedPreferences?.getString("email", ""))
    binding.phoneEditText.setText(sharedPreferences?.getString("phone", ""))

    val id = sharedPreferences?.getString("id", "")
    val rut = sharedPreferences?.getString("rut", "")
    if (rut != null && id != null) {
        val rutSinDigitoVerificador = rut.substring(0, rut.length - 2) // Asumiendo que el dígito verificador y el guion ocupan los dos últimos caracteres
        val nroCliente = "$rutSinDigitoVerificador$id"
        binding.idEditText.setText(nroCliente)
    }

    val imageUri = sharedPreferences?.getString("profile_image_uri", null)
    if (imageUri != null) {
        binding.profileImageView.setImageURI(Uri.parse(imageUri))
    }

    binding.editImageView.setOnClickListener {
        pickImage()
    }

    // Guardar la información actualizada del usuario
    binding.profileImageView.setOnClickListener {
        val editor = sharedPreferences?.edit()
        editor?.putString("name", binding.nameEditText.text.toString())
        editor?.putString("rut", binding.rutEditText.text.toString())
        editor?.putString("address", binding.addressEditText.text.toString())
        editor?.putString("email", binding.emailEditText.text.toString())
        editor?.putString("phone", binding.phoneEditText.text.toString())
        editor?.putString("id", id) // ID no debe cambiar
        editor?.apply()
    }
}

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }


override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}