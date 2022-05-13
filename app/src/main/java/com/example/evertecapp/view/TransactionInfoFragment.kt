package com.example.evertecapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.evertecapp.R
import com.example.evertecapp.model.*
import com.example.evertecapp.utils.Commons
import com.example.evertecapp.utils.Constants
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModel
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModelFactory

class TransactionInfoFragment : Fragment() {

    //to declare edit texts, button and view model
    private var payerNameEditText: EditText? = null
    private var payerEmailEditText: EditText? = null
    private var payerPhoneEditText: EditText? = null
    private var cardNumberEditText: EditText? = null
    private var expiryDateEditText: EditText? = null
    private var cvvEditText: EditText? = null
    private var amountEditText: EditText? = null
    private var sendTransactionButton: Button? = null
    private var transactionInfoFragmentViewModel: TransactionInfoFragmentViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //to inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_transaction_info, container, false)

        //to set fragment edit texts and button
        payerNameEditText = rootView.findViewById(R.id.payer_name_edit_text)
        payerEmailEditText = rootView.findViewById(R.id.payer_email_edit_text)
        payerPhoneEditText = rootView.findViewById(R.id.payer_phone_edit_text)
        cardNumberEditText = rootView.findViewById(R.id.card_number_edit_text)
        expiryDateEditText = rootView.findViewById(R.id.expiry_date_edit_text)
        cvvEditText = rootView.findViewById(R.id.cvv_edit_text)
        amountEditText = rootView.findViewById(R.id.amount_edit_text)
        sendTransactionButton = rootView.findViewById(R.id.send_transaction_button)

        //to create view model factory
        var transactionInfoFragmentViewModelFactory: TransactionInfoFragmentViewModelFactory? = activity?.let {
            TransactionInfoFragmentViewModelFactory.createFactory(it)
        }

        //to create view model
        transactionInfoFragmentViewModel = ViewModelProviders.of(this, transactionInfoFragmentViewModelFactory).get(TransactionInfoFragmentViewModel::class.java)

        //to observe transactionResponse live data
        transactionInfoFragmentViewModel?.transactionResponse?.observe(viewLifecycleOwner){ response ->
            //to add required information about transaction response in local storage
            if(response != null) {
                transactionInfoFragmentViewModel?.insert(getTransaction(response))
            }
        }

        //to observe transactionProccessFailed live data
        transactionInfoFragmentViewModel?.transactionProccessFailed?.observe(viewLifecycleOwner){ error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }

        //to observe storageFailed live data
        transactionInfoFragmentViewModel?.storageFailed?.observe(viewLifecycleOwner){ error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }

        //to send transaction request when send authorization button is clicked
        sendTransactionButton?.setOnClickListener(){

            if(validateFields()) {
                try{
                    //to validate if amount is numeric
                    Integer.parseInt(amountEditText?.getText().toString())
                    transactionInfoFragmentViewModel?.sendTransaction(getTransactionRequest())
                }catch (e: Exception){
                    Toast.makeText(context, "Amount should be a number.",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Some field is empty, please review.",Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    //to build and return a transaction object
    fun getTransaction(response: TransactionResponse): Transaction{
        val status = response.status
        val amount = response.amount
        return Transaction(0, response.reference, amount.total, payerNameEditText?.text.toString(), payerEmailEditText?.text.toString(), payerPhoneEditText?.text.toString(), cardNumberEditText?.text.toString(), status.status)
    }

    //to validate fields value
    fun validateFields(): Boolean{
        var success = true
        if(payerNameEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(payerEmailEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(payerPhoneEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(cardNumberEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(expiryDateEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(payerNameEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        if(amountEditText?.text.toString().isNullOrEmpty()){
            success = false
        }
        return success
    }

    //to build and return a transaction request
    fun getTransactionRequest(): TransactionRequest{

        //to codificate authorization info
        val nonce = Commons.getRandom()
        val seed = Commons.getCurrentDateFormat()
        val nonceBase64 = Commons.getBase64(nonce.toByteArray())
        val tranKeyBase64 = Commons.getBase64(Commons.getSHA256(nonce + seed + Constants.placeToPaytranKey))

        val authorization = Authorization(Constants.placeToPayLogin, tranKeyBase64, nonceBase64, seed)

        val payer = Payer(payerNameEditText?.text.toString(), Constants.payerSurname, payerEmailEditText?.text.toString(), Constants.payerDocumentType, Constants.payerDocumentNumber, payerPhoneEditText?.text.toString())

        val amount = Amount(Constants.paymentCurrency, Integer.parseInt(amountEditText?.text.toString()))
        val payment = Payment(Constants.paymentReference, Constants.paymentDescription, amount)

        val card = Card(cardNumberEditText?.text.toString(), expiryDateEditText?.text.toString(), cvvEditText?.text.toString(),3)
        val instrument = Instrument(card)

        return TransactionRequest(authorization, payer, payment, instrument, Constants.transactionRequestIP, Constants.transactionRequestUserAgent)
    }
}
