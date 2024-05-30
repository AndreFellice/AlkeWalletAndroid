package com.example.alkewalletandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkewalletandroid.model.entities.Transaction
import com.example.alkewalletandroid.model.repository.TransactionRepository
import kotlinx.coroutines.launch

class Pantalla5HomeViewModel : ViewModel(){

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userPhotoUri =  MutableLiveData<String?>().apply {
        value = null // Inicialmente no hay foto de usuario
    }
    val userPhotoUri: LiveData<String?> = _userPhotoUri

    private val _userBalance = MutableLiveData<Double>()
    val userBalance: LiveData<Double> = _userBalance

    private val repository = TransactionRepository()
    val userTransactions: LiveData<List<Transaction>> = repository.transactions

    init {
        fetchTransactions()
    }

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setUserPhotoUri(uri: String?) {
        _userPhotoUri.value = uri
    }

    fun setUserBalance(balance: Double) {
        _userBalance.value = balance
    }


    private fun fetchTransactions() {
        viewModelScope.launch {
            repository.fetchTransactions()
        }
    }
}