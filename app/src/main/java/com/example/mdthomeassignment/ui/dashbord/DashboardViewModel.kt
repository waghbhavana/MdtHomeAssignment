package com.example.mdthomeassignment.ui.dashbord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdthomeassignment.data.model.BalanceResponse
import com.example.mdthomeassignment.data.model.TransactionsResponse
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {

    private val _balance: MutableLiveData<Resource<BalanceResponse>> = MutableLiveData()
    val balance: LiveData<Resource<BalanceResponse>>
        get() = _balance

    private val _transactions: MutableLiveData<Resource<TransactionsResponse>> = MutableLiveData()
    val transactions: LiveData<Resource<TransactionsResponse>>
        get() = _transactions

    fun getBalance() = viewModelScope.launch {
        _balance.value = Resource.Loading
        _balance.value = repository.getBalance()
    }
    fun getTransactions() = viewModelScope.launch {
        _transactions.value = Resource.Loading
        _transactions.value = repository.getTransactions()
    }
    suspend fun clearDataStore() {
        repository.clearDatastore()
    }
}