package com.example.mdthomeassignment.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.mdthomeassignment.data.UserPreferences
import com.example.mdthomeassignment.data.network.RetrofitClient
import com.example.mdthomeassignment.data.repository.BaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<vm : ViewModel, b : ViewBinding, r : BaseRepository> : Fragment() {

    protected lateinit var binding: b
    protected lateinit var viewModel: vm
    protected val retrofitClient = RetrofitClient()
    protected lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        lifecycleScope.launch {
            userPreferences.authToken.first()
            userPreferences.username.first()
        }
        return binding.root
    }

    abstract fun getViewModel(): Class<vm>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): b

    abstract fun getFragmentRepository(): r
}
