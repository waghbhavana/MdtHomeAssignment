package com.example.mdthomeassignment.data.model

data class LoginResponse(
    val accountNo: String,
    val status: String,
    val token: String,
    val username: String
)