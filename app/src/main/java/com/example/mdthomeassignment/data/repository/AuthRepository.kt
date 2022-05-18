package com.example.mdthomeassignment.data.repository

import com.example.mdthomeassignment.data.UserPreferences
import com.example.mdthomeassignment.data.network.AuthApi

class AuthRepository(
    private var api: AuthApi,
    private var preferences: UserPreferences
) : BaseRepository() {

    suspend fun login( username: String, password: String
    ) = safeApiCall {
        api.login(username, password)
    }

    suspend fun register( username: String, password: String
    ) = safeApiCall {
        api.register(username, password)
    }


    suspend fun saveToken(token: String) {
        preferences.saveAuthToken(token)
    }
    suspend fun saveUsername(username: String) {
        preferences.saveUsername(username)
    }
}