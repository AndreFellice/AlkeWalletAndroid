package com.example.alkewalletandroid.view.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.alkewalletandroid.R
import com.example.alkewalletandroid.databinding.ActivityMainBinding
import com.example.alkewalletandroid.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupViews()
        observeViewModel()
        setOnApplyWindowInsets()
        setupBackButtonHandler()
    }

    private fun setupViews() {
        binding.videoView.setMediaController(null)
        binding.logo.setOnClickListener {
            viewModel.onLogoClicked()
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToWelcomeScreen.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToWelcomeScreen()
                viewModel.doneNavigating()
            }
        })

        viewModel.videoUri.observe(this, Observer<Uri> { uri ->
            binding.videoView.setVideoURI(uri)
            binding.videoView.setOnCompletionListener {
                viewModel.onVideoCompleted()
            }
            binding.videoView.start()
        })
    }

    private fun navigateToWelcomeScreen() {
        val intent = Intent(this, Pantalla2WelcomeActivity::class.java)
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
                // Ocultar el teclado si está visible
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)

                if (isEnabled) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}