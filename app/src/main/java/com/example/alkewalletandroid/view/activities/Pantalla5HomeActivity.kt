package com.example.alkewalletandroid.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla5HomeBinding
import com.example.alkewalletandroid.view.adapters.TransaccionesAdapter
import com.example.alkewalletandroid.view.fragments.NotificacionesFragment
import com.example.alkewalletandroid.viewmodel.Pantalla5HomeViewModel


class Pantalla5HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPantalla5HomeBinding
    private val pantalla5HomeViewModel: Pantalla5HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityPantalla5HomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        // Configurar el RecyclerView para las últimas transacciones
        setupRecyclerView()
        // Configurar los observadores
        setupObservers()
        // Configurar los listeners de los botones
        setupListeners()

        // Obtén la URI de la foto del intent y actualiza el ViewModel
        val userPhotoUriString = intent.getStringExtra("user_photo_uri")
        pantalla5HomeViewModel.setUserPhotoUri(userPhotoUriString)
    }

    private fun setupObservers() {
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
        // Observa los cambios en el saldo del usuario
        pantalla5HomeViewModel.userBalance.observe(this, Observer { balance ->
            binding.balance.setText(balance.toString())
        })

        // Observa los cambios en las transacciones del usuario
        pantalla5HomeViewModel.userTransactions.observe(this, Observer { transactions ->
            transactions?.let {
                (binding.recyclerViewTransacciones.adapter as? TransaccionesAdapter)?.submitList(it)
            }
        })
    }

    private fun setupListeners() {

        binding.campana.setOnClickListener {
            // Desplegar un fragmento con las últimas notificaciones del usuario
            loadFragment(NotificacionesFragment())
        }

        binding.btn8.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla8SendMActivivity::class.java)
            startActivity(intent)
        })

        binding.btn9.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Pantalla5HomeActivity, Pantalla9RequestMActivity::class.java)
            startActivity(intent)
        })

        binding.logo5.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        })

        binding.btnPerfil.setOnClickListener(View.OnClickListener {
            val intent =
                Intent(this@Pantalla5HomeActivity, Pantalla7ProfileActivity::class.java)
            startActivity(intent)
        })
    }
    private fun loadFragment(fragment: Fragment) {
        // Cargar el fragmento de notificaciones
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun setupRecyclerView() {
        binding.recyclerViewTransacciones.apply {
            layoutManager = LinearLayoutManager(this@Pantalla5HomeActivity)
            adapter = TransaccionesAdapter()

        }

    }

}


