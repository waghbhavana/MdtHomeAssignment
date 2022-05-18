package com.example.mdthomeassignment.data.network

import com.example.mdthomeassignment.data.model.LoginResponse
import com.example.mdthomeassignment.data.model.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/login")
  suspend  fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse


    @FormUrlEncoded
    @POST("/register")
    suspend  fun register(
        @Field("username") username: String,
        @Field("password") password: String,
    ): RegisterResponse

}