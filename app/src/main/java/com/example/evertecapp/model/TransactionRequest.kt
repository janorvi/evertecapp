package com.example.evertecapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionRequest(
    @SerializedName("auth") val authorization: Authorization,
    @SerializedName("payer") val payer: Payer,
    @SerializedName("payment") val payment: Payment,
    @SerializedName("instrument") val instrument: Instrument,
    @SerializedName("ipAddress") val ipAddress: String,
    @SerializedName("userAgent") val userAgent: String){
}

data class Authorization(
    @Expose @SerializedName ("login") val login: String,
    @SerializedName("tranKey") val tranKey: String,
    @SerializedName("nonce") val nonce: String,
    @SerializedName("seed") val seed: String){
}

data class Payer(
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("email") val email: String,
    @SerializedName("documentType") val documentType: String,
    @SerializedName("document") val document: String,
    @SerializedName("mobile") val mobile: String){
}

data class Payment(
    @SerializedName("reference") val reference: String,
    @SerializedName("description") val description: String,
    @SerializedName("amount") val amount: Amount){
}

data class Amount(
    @SerializedName("currency") val currency: String,
    @SerializedName("total") val total: Int){
}

data class Instrument(
    @SerializedName("card") val card: Card){
}

data class Card(
    @SerializedName("number") val number: String,
    @SerializedName("expiration") val expiration: String,
    @SerializedName("cvv") val cvv: String,
    @SerializedName("instalments") val instalments: Int){
}





