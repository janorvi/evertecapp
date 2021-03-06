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
    val transactionProcessFailed: MutableLiveData<String> = MutableLiveData()
    val transactionResponse: MutableLiveData<TransactionResponse> = MutableLiveData()
    val insertSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val transactionList: MutableLiveData<List<Transaction>> = MutableLiveData()
    val transaction: MutableLiveData<Transaction> = MutableLiveData()

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

    fun getTransactionByNumber(number: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = transactionRepository.geTransactionByNumber(number)
                if(response != null){
                    transaction.postValue(response)
                }else{
                    storageFailed.postValue("Storage process was failed.")
                }
            }catch (e: Exception){
                storageFailed.postValue("Storage process was failed: $e")
            }
        }
    }

    fun getAllTransactions() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = transactionRepository.getAllTransactions()
                if(response != null){
                    transactionList.postValue(response)
                }else{
                    storageFailed.postValue("Storage process was failed.")
                }
            }catch (e: Exception){
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
                    transactionProcessFailed.postValue("Transaction process was failed: " + response?.errorBody().toString())
                }
            }catch(e: Exception){
                transactionProcessFailed.postValue("Transaction process was failed: $e")
            }
        }
    }
}