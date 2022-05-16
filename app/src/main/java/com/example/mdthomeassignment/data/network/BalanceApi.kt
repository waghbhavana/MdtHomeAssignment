package com.example.mdthomeassignment.data.network

import com.example.mdthomeassignment.data.model.BalanceResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface BalanceApi {

    @GET("/balance")
    suspend  fun getBalance(): BalanceResponse
}