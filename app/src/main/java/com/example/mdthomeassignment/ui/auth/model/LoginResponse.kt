package com.example.mdthomeassignment.ui.auth.model

data class LoginResponse(
    val accountNo: String,
    val status: String,
    val token: String,
    val username: String
)