package com.example.alkewalletandroid.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityPantalla2WelcomeBinding
import com.example.alkewalletandroid.viewmodel.Pantalla2WelcomeViewModel

class Pantalla2WelcomeActivity : AppCompatActivity() {

    private val viewModel: Pantalla2WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityPantalla2WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pantalla2_welcome)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupViews()
        observeViewModel()
        setOnApplyWindowInsets()
        setupBackButtonHandler()
    }

    private fun setupViews() {
        binding.videoView2.setMediaController(MediaController(this).apply {
            setAnchorView(binding.videoView2)
        })

        // Comenzar la reproducción del video cuando se carga
        binding.videoView2.setOnPreparedListener { it.start() }

        binding.btn1.setOnClickListener {
            viewModel.onSignupClicked()
        }

        binding.btn2.setOnClickListener {
            viewModel.onLoginClicked()
        }

        binding.logo2.setOnClickListener {
            viewModel.onLogoClicked()
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToSignup.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToSignupScreen()
                viewModel.doneNavigating()
            }
        })

        viewModel.navigateToLogin.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToLoginScreen()
                viewModel.doneNavigating()
            }
        })

        viewModel.navigateToMain.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToMainScreen()
                viewModel.doneNavigating()
            }
        })

        viewModel.videoUri.observe(this, Observer { uri ->
            binding.videoView2.setVideoURI(uri)
            binding.videoView2.start()
        })
    }

    private fun navigateToSignupScreen() {
        val intent = Intent(this, Pantalla4SignupActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, Pantalla3LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setOnApplyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBackButtonHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)

                if (isEnabled) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}