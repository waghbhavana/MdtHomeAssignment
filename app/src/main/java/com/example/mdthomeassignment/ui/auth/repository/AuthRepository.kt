package com.example.mdthomeassignment.ui.auth.repository

import com.example.mdthomeassignment.ui.auth.network.AuthApi

class AuthRepository(
    private var api: AuthApi
) : BaseRepository() {

   suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username,password)
   }
}