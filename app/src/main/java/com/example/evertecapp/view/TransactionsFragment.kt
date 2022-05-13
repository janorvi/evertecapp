package com.example.evertecapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evertecapp.R
import com.example.evertecapp.model.Transaction
import com.example.evertecapp.model.TransactionItem
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModel
import com.example.evertecapp.viewmodel.TransactionInfoFragmentViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var numberEditText: EditText? = null
    private var searchButton: Button? = null

    private var transactionViewModel: TransactionInfoFragmentViewModel? = null

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
        var rootView = inflater.inflate(R.layout.fragment_transactions, container, false)

        var authorizationViewModelFactory: TransactionInfoFragmentViewModelFactory? =
            activity?.let { TransactionInfoFragmentViewModelFactory.createFactory(it) }

        transactionViewModel = ViewModelProviders.of(this, authorizationViewModelFactory).get(TransactionInfoFragmentViewModel::class.java)

        var groupAdapter = GroupAdapter<GroupieViewHolder>()

        var recyclerView: RecyclerView? = null

        numberEditText = rootView.findViewById(R.id.number_edit_text)
        searchButton = rootView.findViewById(R.id.search_button)

        recyclerView = rootView.findViewById(R.id.transactions_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        transactionViewModel?.getAllTransactions()
        transactionViewModel?.transactionList?.observe(viewLifecycleOwner){ transactionList ->
            for(transaction in transactionList){
                groupAdapter.add(TransactionItem(transaction))
            }
            recyclerView.adapter = groupAdapter
        }

        //to observe transaction live data
        transactionViewModel?.transaction?.observe(viewLifecycleOwner){ transaction ->
            if(transaction != null) {
                val transactionItem = TransactionItem(transaction)
                groupAdapter.clear()
                groupAdapter.add(transactionItem)
                recyclerView.adapter = groupAdapter
            }else {
                Toast.makeText(context,"Don't exists any authorization with the selected number.", Toast.LENGTH_LONG).show()
            }
        }

        searchButton?.setOnClickListener{
            if(!numberEditText?.text.toString().isNullOrEmpty()) {
                transactionViewModel?.getTransactionByNumber(Integer.parseInt(numberEditText?.text.toString()))
            }else{
                transactionViewModel?.getAllTransactions()
                Toast.makeText(context,"Number field is empty.", Toast.LENGTH_LONG).show()
            }
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
         * @return A new instance of fragment TransactionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransactionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}