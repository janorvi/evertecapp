package com.example.evertecapp.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("status") val status: Status,
    @SerializedName("reference") val reference: String,
    @SerializedName("amount") val amount: AmountResponse){
}

data class Status(
    @SerializedName("status") val status: String,
    @SerializedName("reason") val reason: String,
    @SerializedName("message") val message: String,
    @SerializedName("date") val date: String){
}

data class AmountResponse(
    @SerializedName("total") val total: Int){
}