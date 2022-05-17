package com.example.mdthomeassignment.data.network

import com.example.mdthomeassignment.data.model.TransactionsResponse
import retrofit2.http.GET

interface TransactionsApi {
    @GET("/transactions")
    suspend  fun getTransactions(): TransactionsResponse
}