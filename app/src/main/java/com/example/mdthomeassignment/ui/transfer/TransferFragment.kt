package com.example.mdthomeassignment.ui.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.mdthomeassignment.R
import com.example.mdthomeassignment.data.model.DataX
import com.example.mdthomeassignment.data.model.PayeeResponse
import com.example.mdthomeassignment.data.network.BalanceApi
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.network.TransactionsApi
import com.example.mdthomeassignment.data.network.TransferApi
import com.example.mdthomeassignment.data.repository.DashboardRepository
import com.example.mdthomeassignment.data.repository.TransferRepository
import com.example.mdthomeassignment.databinding.FragmentTransferBinding
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.util.handleApiError
import com.example.mdthomeassignment.util.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Response


class TransferFragment : BaseFragment<TransferViewModel,FragmentTransferBinding,TransferRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPayees()
        viewModel.payees.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    Toast.makeText(context,it.value.toString(),Toast.LENGTH_LONG).show()
                    setPayeeData(it.value.data)
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
    }
    private fun setPayeeData(payees:List<DataX>){
        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,payees) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.edittextPayee.adapter = adapter


    }
    override fun getViewModel() = TransferViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransferBinding = FragmentTransferBinding.inflate(inflater,container,false)


    override fun getFragmentRepository(): TransferRepository {
        val api = retrofitClient.buildApi(TransferApi::class.java)
        return TransferRepository(api)
    }

}