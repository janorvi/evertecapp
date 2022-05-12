package com.example.evertecapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evertecapp.model.Transaction
import com.example.evertecapp.model.TransactionRequest
import com.example.evertecapp.model.TransactionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class TransactionInfoFragmentViewModel(
    var transactionRepository: TransactionRepository
): ViewModel(){
    val storageFailed: MutableLiveData<String> = MutableLiveData()
    val transactionProccessFailed: MutableLiveData<String> = MutableLiveData()
    val transactionResponse: MutableLiveData<TransactionResponse> = MutableLiveData()
    val insertSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun insert(transaction: Transaction) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = transactionRepository.insert(transaction)
                if(response != null){
                    insertSuccess.postValue(true)
                }else{
                    storageFailed.postValue("Storage process was failed.")
                }
            }catch(e: Exception){
                storageFailed.postValue("Storage process was failed: $e")
            }
        }
    }

    fun sendTransaction(transactionRequest: TransactionRequest){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = transactionRepository.sendTransaction(transactionRequest)
                if (response?.body() != null) {
                    transactionResponse.postValue(response?.body())
                }else{
                    transactionProccessFailed.postValue("Transaction process was failed: " + response?.errorBody().toString())
                }
            }catch(e: Exception){
                transactionProccessFailed.postValue("Transaction process was failed: $e")
            }
        }
    }
}