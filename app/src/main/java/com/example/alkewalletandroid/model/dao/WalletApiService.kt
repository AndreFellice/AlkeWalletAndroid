package com.example.alkewalletandroid.model.dao

import com.example.alkewalletandroid.model.entities.Transaction
import retrofit2.Call
import retrofit2.http.GET

interface WalletApiService {
    @GET("transactions")
    fun getTransactions(): Call<List<Transaction>>

}