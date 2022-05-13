package com.example.evertecapp.viewmodel

import android.content.Context
import com.example.evertecapp.api.ApiService
import com.example.evertecapp.api.RetrofitClientService
import com.example.evertecapp.database.TransactionDAO
import com.example.evertecapp.database.TransactionDatabase
import com.example.evertecapp.model.Transaction
import com.example.evertecapp.model.TransactionRequest
import retrofit2.Retrofit

class TransactionRepository(
    private val transactionDAO: TransactionDAO?
) {
    private val retrofitClientService: Retrofit? = RetrofitClientService.getInstance()
    private val apiService: ApiService? = retrofitClientService?.create(ApiService::class.java)
    companion object{
        var INSTANCE: TransactionRepository? = null
        fun getInstance(context: Context): TransactionRepository? {
            if(INSTANCE == null){
                synchronized(TransactionRepository::class){
                    if(INSTANCE == null){
                        var transactionDatabase: TransactionDatabase? = TransactionDatabase.getInstance(context)
                        INSTANCE = TransactionRepository(transactionDatabase?.transactionDao())
                    }
                }
            }
            return INSTANCE
        }
    }

    suspend fun insert(transaction: Transaction) = transactionDAO?.insert(transaction)

    suspend fun getAllTransactions() = transactionDAO?.getAllTransactions()

    suspend fun geTransactionByNumber(number: Int) = transactionDAO?.getTransactionByNumber(number)

    suspend fun sendTransaction(transactionRequest: TransactionRequest) = apiService?.sendTransaction(transactionRequest)
}