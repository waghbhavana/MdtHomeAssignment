package com.example.mdthomeassignment.data.repository

import com.example.mdthomeassignment.data.UserPreferences
import com.example.mdthomeassignment.data.network.BalanceApi

class BalanceRepository(
    private var api: BalanceApi,
    private var preferences: UserPreferences
) : BaseRepository() {

    suspend fun getBalance() = safeApiCall {
        api.getBalance()
    }

    suspend fun clearDatastore() {
        preferences.clearDatastore()
    }
}