package com.example.evertecapp.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("status") val status: Status){
}

data class Status(
    @SerializedName("status") val status: String,
    @SerializedName("reason") val reason: String,
    @SerializedName("message") val message: String,
    @SerializedName("date") val date: String){
}