package com.example.mdthomeassignment.data.model

data class Transaction (
    val transactionDate: String,
    val amount: Double,
     val receipientDetail: List<Receipient>

)