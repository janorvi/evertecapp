package com.example.evertecapp.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.evertecapp.R
import com.example.evertecapp.model.Transaction

private const val TRANSACTION = "transaction"

class TransactionDialogFragment: DialogFragment() {
    private var transaction: Transaction? = null

    companion object {
        @JvmStatic
        fun newInstance(transaction: Transaction) = TransactionDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TRANSACTION, transaction)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            transaction = it.getSerializable(TRANSACTION) as Transaction?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.dialog_fragment_transaction, container, false)

        var textViewPayerName = rootview.findViewById<TextView>(R.id.text_view_payer_name)
        var textViewPayerEmail = rootview.findViewById<TextView>(R.id.text_view_payer_email)
        var textViewPayerPhone = rootview.findViewById<TextView>(R.id.text_view_payer_phone)
        var textViewCardNumber = rootview.findViewById<TextView>(R.id.text_view_card_number)
        var textViewAmount = rootview.findViewById<TextView>(R.id.text_view_amount)
        var textViewStatus = rootview.findViewById<TextView>(R.id.text_view_status)

        var okButton = rootview.findViewById<Button>(R.id.button_ok)

        textViewPayerName.text = transaction?.payerName
        textViewPayerEmail.text = transaction?.payerEmail
        textViewPayerPhone.text = "Phone: " +transaction?.payerPhone
        //textViewCardNumber.text = "Card No.: " + transaction?.cardNumber
        textViewCardNumber.text = "Card No.: " + transaction?.cardNumber?.substring(0,4) + "*********"
        textViewAmount.text = "Amount: " + transaction?.amountTotal.toString()
        textViewStatus.text = transaction?.status

        okButton.setOnClickListener(){
            dismiss()
        }
        return rootview
    }
}