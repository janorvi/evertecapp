package com.example.evertecapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evertecapp.model.TransactionRequest
import com.example.evertecapp.model.TransactionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionInfoFragmentViewModel(
    var transactionRepository: TransactionRepository
): ViewModel(){
    val transactionResponse:MutableLiveData<TransactionResponse> = MutableLiveData()

    fun sendTransaction(transactionRequest: TransactionRequest){
        CoroutineScope(Dispatchers.IO).launch {
            val response = transactionRepository.sendTransaction(transactionRequest)
            if(response != null){
                transactionResponse.postValue(response.body())
            }
        }
    }
}