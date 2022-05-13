package com.example.evertecapp.model

import android.widget.TextView
import com.example.evertecapp.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TransactionItem(
    val transaction: Transaction
): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val textViewTransactionId: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_id)
        val textViewAmount: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_amount)
        val textViewCardnumber: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_card_number)
        val textViewStatus: TextView = viewHolder.itemView.findViewById(R.id.text_view_transaction_status)
        textViewTransactionId.text = String.format("%06d",transaction.id)
        textViewAmount.text = transaction?.amountTotal.toString()
        textViewCardnumber.text = transaction?.cardNumber.substring(0,4) + "*********"
        textViewStatus.text = transaction.status
    }

    override fun getLayout() = R.layout.transaction_item
}