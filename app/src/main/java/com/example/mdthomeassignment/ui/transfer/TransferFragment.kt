package com.example.mdthomeassignment.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.mdthomeassignment.data.model.DataX
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.data.network.TransferApi
import com.example.mdthomeassignment.data.repository.TransferRepository
import com.example.mdthomeassignment.databinding.FragmentTransferBinding
import com.example.mdthomeassignment.ui.base.BaseFragment
import com.example.mdthomeassignment.util.enable
import com.example.mdthomeassignment.util.handleApiError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class TransferFragment :
    BaseFragment<TransferViewModel, FragmentTransferBinding, TransferRepository>() {

    lateinit var dataX: DataX
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTransferNow.enable(false)
        viewModel.getPayees()
        viewModel.payees.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    setPayeeData(it.value.data)
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
        viewModel.transfer.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> handleApiError(it)
            }
        })
        binding.editTextDescription.addTextChangedListener {

            binding.buttonTransferNow.enable(binding.editTextDescription.text.isNotEmpty() && it.toString().isNotEmpty())
        }
        binding.transferBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setPayeeData(payees: List<DataX>) {
        val adapter = context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item,
                payees.map { unit ->
                    unit.name
                })
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.edittextPayee.adapter = adapter


        binding.edittextPayee.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                dataX = payees[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
        binding.buttonTransferNow.setOnClickListener {
            val accountNumber = dataX.accountNo
            val amount = binding.edittextAmount.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            when {
                amount.isEmpty() -> binding.edittextAmount.error = "Enter Amount"
                description.isEmpty() -> binding.editTextDescription.error = "Enter description"
                else -> viewModel.transfer(accountNumber, amount.toInt(), description)
            }
        }
    }

    override fun getViewModel() = TransferViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransferBinding = FragmentTransferBinding.inflate(inflater, container, false)


    override fun getFragmentRepository(): TransferRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(TransferApi::class.java, token.toString())
        return TransferRepository(api)
    }

}