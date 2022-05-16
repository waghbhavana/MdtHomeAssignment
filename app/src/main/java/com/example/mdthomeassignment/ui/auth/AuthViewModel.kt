package com.example.mdthomeassignment.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdthomeassignment.data.model.LoginResponse
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResponseData: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val loginResponseData: LiveData<Resource<LoginResponse>>
        get() = _loginResponseData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResponseData.value = repository.login(username, password)
        }
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveToken(token)
    }

    suspend fun saveUsername(username: String) {
        repository.saveUsername(username)
    }

}