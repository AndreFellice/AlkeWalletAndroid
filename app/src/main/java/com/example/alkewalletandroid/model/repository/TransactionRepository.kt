package com.example.alkewalletandroid.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alkewalletandroid.model.RetrofitClient
import com.example.alkewalletandroid.model.entities.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionRepository {
    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> = _transactions

    fun fetchTransactions() {
        RetrofitClient.instance.getTransactions().enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                if (response.isSuccessful) {
                    _transactions.postValue(response.body())
                } else {
                    _transactions.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                _transactions.postValue(emptyList())
            }
        })
    }
}
