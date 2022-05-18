package com.example.mdthomeassignment.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mdthomeassignment.data.network.AuthApi
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.AuthRepository
import com.example.mdthomeassignment.databinding.FragmentRegisterBinding
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.ui.dashbord.DashboardActivity
import com.example.mdthomeassignment.util.enable
import com.example.mdthomeassignment.util.handleApiError
import com.example.mdthomeassignment.util.startNewActivity
import kotlinx.coroutines.launch

class RegisterFragment :
    BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButtonRegister.enable(false);
        binding.registerButtonRegister.setOnClickListener {
            val username = binding.registerEditTextUsername.text.toString().trim()
            val password = binding.registerEditTextPassword.text.toString().trim()
            val confirmPassword = binding.registerEditTextConfirmPassword.text.toString().trim()
            when {
                username.isEmpty() -> binding.registerEditTextUsername.error = "Enter username"
                password.isEmpty() -> binding.registerEditTextPassword.error = "Enter Password"
                confirmPassword.isEmpty() -> binding.registerEditTextConfirmPassword.error = "Enter confirm Password"
                (password != confirmPassword)-> binding.registerEditTextConfirmPassword.error = "Password and confirm Password are not same"
                else -> viewModel.register(username, password)
            }
        }
        viewModel.registerResponseData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch{
                        viewModel.saveAuthToken(it.value.token)
                        requireActivity().startNewActivity(DashboardActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
        binding.registerEditTextConfirmPassword.addTextChangedListener {

            binding.registerButtonRegister.enable(binding.registerEditTextConfirmPassword.text.isNotEmpty() && it.toString().isNotEmpty())
        }
        binding.registerBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()=AuthRepository(retrofitClient.buildApi(AuthApi::class.java), userPreferences)


}