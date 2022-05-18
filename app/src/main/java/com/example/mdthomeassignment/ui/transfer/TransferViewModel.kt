package com.example.mdthomeassignment.ui.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdthomeassignment.data.model.PayeeResponse
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.TransferRepository
import kotlinx.coroutines.launch

class TransferViewModel(
    private val transferRepository: TransferRepository):ViewModel() {

    private val _payees: MutableLiveData<Resource<PayeeResponse>> = MutableLiveData()
    val payees: LiveData<Resource<PayeeResponse>>
        get() = _payees

    private val _transfer: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val transfer: LiveData<Resource<Unit>>
        get() = _transfer

    fun getPayees()=viewModelScope.launch {
        _payees.value =transferRepository.getPayees()
    }

    fun transfer(receipientAccountNo:String, amount: Int, description: String)=viewModelScope.launch {
        _transfer.value=transferRepository.transfer(receipientAccountNo,amount, description)
    }
}