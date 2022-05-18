package com.example.mdthomeassignment.ui.dashbord

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdthomeassignment.R
import com.example.mdthomeassignment.data.model.BalanceResponse
import com.example.mdthomeassignment.data.model.Data
import com.example.mdthomeassignment.data.model.TransactionsResponse
import com.example.mdthomeassignment.data.network.BalanceApi
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.network.TransactionsApi
import com.example.mdthomeassignment.data.repository.DashboardRepository
import com.example.mdthomeassignment.databinding.FragmentDashbordBinding
import com.example.mdthomeassignment.ui.adapter.TransactionAdapter
import com.example.mdthomeassignment.ui.auth.AuthActivity
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.ui.transfer.TransferActivity
import com.example.mdthomeassignment.util.handleApiError
import com.example.mdthomeassignment.util.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashbordBinding, DashboardRepository>() {

    private lateinit var transactionAdapter: TransactionAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)

        viewModel.getBalance()
        viewModel.getTransactions()
        viewModel.balance.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    updateDashboard(it)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
        viewModel.transactions.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is Resource.Success -> {
                        val updateList = it.value.data.groupBy { dataObj ->
                            dataObj.transactionDate.subSequence(0, 10)
                        }
                        val sortedList: List<List<Data>> = updateList.values.toList()
                        initRecyclerView()
                        context?.let { it1 ->
                            transactionAdapter.setTransactionList(
                                sortedList,
                                it1
                            )
                        }
                    }
                    is Resource.Failure -> handleApiError(it)
                }
            })
        binding.buttonLogout.setOnClickListener {
            lifecycleScope.launch {
                viewModel.clearDataStore()
                startActivity(Intent(context, AuthActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        }
        binding.buttonMakeTrasnfer.setOnClickListener {
            startActivity(Intent(context,TransferActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        binding.transactionHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionAdapter = TransactionAdapter()
        binding.transactionHistoryRecyclerView.adapter = transactionAdapter
    }

    private fun updateDashboard(it: Resource.Success<BalanceResponse>) {

        val username = runBlocking { userPreferences.username.first() }
        binding.accountNoText.text = it.value.accountNo.trim()
        binding.balanceAmount.text = getString(R.string.sgd) + it.value.balance.toString().trim()
        binding.accountHolderTextt.text = username.toString()
    }


    override fun getViewModel() = DashboardViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDashbordBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): DashboardRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(BalanceApi::class.java, token.toString())
        val transactionsApi = retrofitClient.buildApi(TransactionsApi::class.java, token.toString())
        return DashboardRepository(api, userPreferences, transactionsApi)
    }


}