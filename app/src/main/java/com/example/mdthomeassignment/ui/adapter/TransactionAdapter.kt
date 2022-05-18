package com.example.mdthomeassignment.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mdthomeassignment.R
import com.example.mdthomeassignment.data.model.Data
import com.example.mdthomeassignment.util.convertDate

class TransactionAdapter:RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    private lateinit var transactionList: List<List<Data>>
     lateinit var mcontext: Context

    fun setTransactionList(transactionList: List<List<Data>>, context:Context){
        this.transactionList=transactionList
        this.mcontext=context

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionList[position],mcontext)


    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val dateText: TextView =view.findViewById(R.id.dateText)
       private val recyclerView: RecyclerView =view.findViewById(R.id.transactionRecyclerView)

        fun bind(data: List<Data>,context: Context){
            dateText.text= convertDate(data[0].transactionDate)
           val transactionChildAdapter=TransactionChildAdapter()
            recyclerView.layoutManager= LinearLayoutManager(context)
            recyclerView.adapter=transactionChildAdapter
            transactionChildAdapter.setTransactionList(data.toList(),context)

        }
    }
}