package com.example.mdthomeassignment.data.repository

import com.example.mdthomeassignment.data.network.TransferApi

class TransferRepository(
    private var transferApi: TransferApi): BaseRepository() {

        suspend fun getPayees()= safeApiCall{
            transferApi.getPayees()
        }

    suspend fun transfer(receipientAccountNo: String,amount: String,description: String)=safeApiCall {
        transferApi.transfer(receipientAccountNo,amount,description)
    }
}