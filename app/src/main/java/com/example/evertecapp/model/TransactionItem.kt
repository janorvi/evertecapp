package com.example.evertecapp.model

import android.widget.TextView
import com.example.evertecapp.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TransactionItem(
    val transaction: Transaction
): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val textViewAmount: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_amount)
        val textViewStatus: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_status)
        textViewAmount.text = transaction.amountTotal.toString()
        textViewStatus.text = transaction.status
    }

    override fun getLayout() = R.layout.transaction_item
}