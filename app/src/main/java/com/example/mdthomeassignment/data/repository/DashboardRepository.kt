package com.example.mdthomeassignment.data.repository

import com.example.mdthomeassignment.data.UserPreferences
import com.example.mdthomeassignment.data.network.BalanceApi
import com.example.mdthomeassignment.data.network.TransactionsApi

class DashboardRepository(
    private var api: BalanceApi,
    private var preferences: UserPreferences,
    private var transactionsApi: TransactionsApi
) : BaseRepository() {

    suspend fun getBalance() = safeApiCall {
        api.getBalance()
    }
    suspend fun getTransactions() = safeApiCall {
        transactionsApi.getTransactions()
    }
    suspend fun clearDatastore() {
        preferences.clearDatastore()
    }
}