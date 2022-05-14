package com.example.mdthomeassignment.ui.auth.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private const val BASE_URL = "https://green-thumb-64168.uc.r.appspot.com/"

        fun <Api> buildApi(
            api: Class<Api>
        ): Api {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
        }
    }
}