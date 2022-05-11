package com.example.evertecapp.api

import com.example.evertecapp.model.TransactionRequest
import com.example.evertecapp.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("gateway/process")
    suspend fun sendTransaction(@Body request: TransactionRequest): Response<TransactionResponse>
}