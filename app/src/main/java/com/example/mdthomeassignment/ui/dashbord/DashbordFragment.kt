package com.example.mdthomeassignment.ui.dashbord

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mdthomeassignment.R
import com.example.mdthomeassignment.data.model.BalanceResponse
import com.example.mdthomeassignment.data.network.BalanceApi
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.repository.BalanceRepository
import com.example.mdthomeassignment.databinding.FragmentDashbordBinding
import com.example.mdthomeassignment.ui.auth.AuthActivity
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.util.handleApiError
import com.example.mdthomeassignment.util.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DashbordFragment : BaseFragment<DashboardViewModel,FragmentDashbordBinding,BalanceRepository>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBalance()
        viewModel.balance.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success->{
                    updateDashbord(it)
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
        binding.buttonLogout.setOnClickListener {
            lifecycleScope.launch {
                viewModel.clearDataStore()
                startActivity(Intent(context,AuthActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        }
    }

    private fun updateDashbord(it: Resource.Success<BalanceResponse>) {

        val username = runBlocking { userPreferences.username.first() }
        binding.accountNoText.text=it.value.accountNo.toString().trim()
        binding.balanceAmount.text=getString(R.string.sgd)+it.value.balance.toString().trim()
        binding.accountHolderTextt.text=username.toString()
         }



    override fun getViewModel()= DashboardViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=FragmentDashbordBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): BalanceRepository {
        val token = runBlocking { userPreferences.authToken.first() }
       val api= retrofitClient.buildApi(BalanceApi::class.java,token.toString())
        return BalanceRepository(api,userPreferences)
    }


}