package com.example.mdthomeassignment.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mdthomeassignment.ui.auth.AuthViewModel
import com.example.mdthomeassignment.data.repository.AuthRepository
import com.example.mdthomeassignment.data.repository.DashboardRepository
import com.example.mdthomeassignment.data.repository.BaseRepository
import com.example.mdthomeassignment.ui.dashbord.DashboardViewModel

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  when(modelClass){
           AuthViewModel::class.java -> AuthViewModel(repository as AuthRepository) as T

            DashboardViewModel::class.java -> DashboardViewModel(repository as DashboardRepository) as T
            else -> {throw IllegalArgumentException("ViewModel not found")}
        }
    }
}