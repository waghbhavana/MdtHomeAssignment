package com.example.mdthomeassignment.ui.auth.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mdthomeassignment.databinding.FragmentLoginBinding
import com.example.mdthomeassignment.ui.auth.base.BaseFragment
import com.example.mdthomeassignment.ui.auth.network.AuthApi
import com.example.mdthomeassignment.ui.auth.network.Resource
import com.example.mdthomeassignment.ui.auth.repository.AuthRepository


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponseData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
                }
                is Resource.Failure ->{
                    //@ToDO handle API failure
                    Toast.makeText(requireContext()," API failure",Toast.LENGTH_LONG).show()
                }
            }
        })
        binding.buttonLogin.setOnClickListener{

            val username=binding.editTextUsername.text.toString().trim()
            val password=binding.editTextPassword.text.toString().trim()
            Log.d("User"+username,"password"+password)
            Log.d("password"+password,"password"+password)
            //@ToDO handle validation
            viewModel.login(username, password)
        }
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(retrofitClient.buildApi(AuthApi::class.java))



}