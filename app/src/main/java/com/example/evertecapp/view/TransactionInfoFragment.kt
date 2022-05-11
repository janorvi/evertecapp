package com.example.evertecapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.evertecapp.R
import com.example.evertecapp.model.*
import com.example.evertecapp.utils.Commons
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModel
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var transactionInfoFragmentViewModel: TransactionInfoFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_transaction_info, container, false)
        var transactionInfoFragmentViewModelFactory: TransactionInfoFragmentViewModelFactory? = activity?.let {
            TransactionInfoFragmentViewModelFactory.createFactory(it)
        }
        transactionInfoFragmentViewModel = ViewModelProviders.of(this, transactionInfoFragmentViewModelFactory).get(TransactionInfoFragmentViewModel::class.java)

        ///////////////////////////////////
        val nonce = Commons.getRandom()
        val seed = Commons.getCurrentDateFormat()
        val nonceBase64 = Commons.getBase64(nonce.toByteArray())
        val tranKeyBase64 = Commons.getBase64(Commons.getSHA256(nonce + seed + "024h1IlD"))

        val authorization = Authorization("6dd490faf9cb87a9862245da41170ff2",tranKeyBase64,nonceBase64,seed)

        val payer = Payer("Diego","Calle","dnetix@yopmail.com","cc","809284521","3214567")

        val amount = Amount("COP",100)
        val payment = Payment("001","Compra",amount)

        val card = Card("007000000027","12/18","123",3)
        val instrument = Instrument(card)

        val transactionRequest = TransactionRequest(authorization, payer, payment, instrument, "190.85.90.130", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
        ///////////////////////////////////

        transactionInfoFragmentViewModel?.sendTransaction(transactionRequest)
        transactionInfoFragmentViewModel?.transactionResponse?.observe(viewLifecycleOwner){
            val status = it.status
            val transactionStatus = status.status
            val reason = status.message
            Log.i("response_status",transactionStatus)
            Log.i("response_reason",reason)
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TransactionInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransactionInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}