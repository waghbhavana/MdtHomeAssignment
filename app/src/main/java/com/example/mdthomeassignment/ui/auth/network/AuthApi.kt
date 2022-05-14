package com.example.mdthomeassignment.ui.auth.network

import com.example.mdthomeassignment.ui.auth.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/login")
  suspend  fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):LoginResponse


}