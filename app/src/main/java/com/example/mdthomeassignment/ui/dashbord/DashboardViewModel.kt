package com.example.mdthomeassignment.ui.dashbord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdthomeassignment.data.model.BalanceResponse
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.BalanceRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: BalanceRepository) : ViewModel() {

    private val _balance:MutableLiveData<Resource<BalanceResponse>> = MutableLiveData()
    val balance: LiveData<Resource<BalanceResponse>>
    get() = _balance

    fun getBalance()= viewModelScope.launch {
        _balance.value = repository.getBalance()
    }

    suspend fun clearDataStore(){
        repository.clearDatastore()
    }
}