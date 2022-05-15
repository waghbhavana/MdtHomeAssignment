package com.example.mdthomeassignment.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mdthomeassignment.databinding.FragmentLoginBinding
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.data.network.AuthApi
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.AuthRepository
import com.example.mdthomeassignment.ui.dashbord.DashboardActivity
import com.example.mdthomeassignment.util.enable
import com.example.mdthomeassignment.util.startNewActivity
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonLogin.enable(false)
        viewModel.loginResponseData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {

                    viewModel.saveAuthToken(it.value.token)
                    requireActivity().startNewActivity(DashboardActivity::class.java)
                }
                is Resource.Failure -> {
                    //@ToDO handle API failure
                    Toast.makeText(requireContext(), " API failure", Toast.LENGTH_LONG).show()
                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            when {
                username.isEmpty() -> binding.editTextUsername.setError("Enter username")
                password.isEmpty() -> binding.editTextPassword.setError("Enter Password")
                else -> viewModel.login(username, password)
            }
        }
        binding.editTextPassword.addTextChangedListener {

            binding.buttonLogin.enable(binding.editTextUsername.text.isNotEmpty() && it.toString().isNotEmpty())
        }

    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(retrofitClient.buildApi(AuthApi::class.java), userPreferences)


}