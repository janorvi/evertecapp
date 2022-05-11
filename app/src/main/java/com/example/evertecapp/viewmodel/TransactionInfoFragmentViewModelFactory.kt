package com.example.evertecapp.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import java.lang.RuntimeException

class TransactionInfoFragmentViewModelFactory(
    var transactionRepository: TransactionRepository?
):ViewModelProvider.Factory {

    companion object{
        fun createFactory(activity: Activity): TransactionInfoFragmentViewModelFactory {
            var context: Context? = null
            context = activity.applicationContext
            if(context == null){
                throw IllegalStateException("Not yet atached to application")
            }
            return TransactionInfoFragmentViewModelFactory(
                TransactionRepository.getInstance(context)
            )
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try{
            return modelClass.getConstructor(TransactionRepository::class.java).newInstance(transactionRepository)
        }catch (e: InstantiationException){
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}