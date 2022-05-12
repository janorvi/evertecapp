package com.example.evertecapp.api

import com.example.evertecapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientService {
    companion object{
        var INSTANCE: Retrofit? = null
        fun getInstance(): Retrofit? {
            if(INSTANCE == null){
                synchronized(RetrofitClientService::class){
                    if(INSTANCE == null){
                        INSTANCE = Retrofit.Builder()
                            .baseUrl(Constants.placeToPayBaseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}