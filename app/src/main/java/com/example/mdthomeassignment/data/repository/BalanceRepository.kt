package com.example.mdthomeassignment.data.repository

import com.example.mdthomeassignment.data.network.BalanceApi

class BalanceRepository(
    private var api: BalanceApi
) : BaseRepository() {

    suspend fun getBalance() = safeApiCall {
        api.getBalance()
    }

}