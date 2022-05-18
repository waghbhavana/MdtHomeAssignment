package com.example.mdthomeassignment.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Color.green
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mdthomeassignment.R
import com.example.mdthomeassignment.data.model.Data

class TransactionChildAdapter : RecyclerView.Adapter<TransactionChildAdapter.TransactionChildViewHolder>() {
    private lateinit var transactionList: List<Data>
    private lateinit var context: Context


    fun setTransactionList( transactionList: List<Data>,context: Context){
        this.transactionList=transactionList
        this.context= context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionChildAdapter.TransactionChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nested_list_item,parent,false)
        return TransactionChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionChildViewHolder, position: Int) {
        holder.bind(transactionList[position],context)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionChildViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val accountHolder: TextView =view.findViewById(R.id.accountHolder)
        private val accountNo: TextView =view.findViewById(R.id.accountNo)
        private val amount: TextView =view.findViewById(R.id.amount)


        fun bind(data: Data,context: Context) = if(data.transactionType=="transfer") {
            accountHolder.text = data.receipient.accountHolder
            accountNo.text = data.receipient.accountNo.trim()
            amount.text = "-"+data.amount.toString().trim()
        }else{
            accountHolder.text = data.sender.accountHolder
            accountNo.text = data.sender.accountNo.trim()
            amount.setTextColor(context.resources.getColor(R.color.green))
            amount.text = data.amount.toString().trim()
        }
    }

}