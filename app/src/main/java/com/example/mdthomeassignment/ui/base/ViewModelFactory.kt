package com.example.mdthomeassignment.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mdthomeassignment.ui.auth.AuthViewModel
import com.example.mdthomeassignment.data.repository.AuthRepository
import com.example.mdthomeassignment.data.repository.BaseRepository

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> {throw IllegalArgumentException("ViewModel not found")}
        }
    }
}