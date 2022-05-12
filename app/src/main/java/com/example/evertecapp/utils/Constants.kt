package com.example.evertecapp.utils

class Constants {
    companion object{
        //service constants
        const val placeToPayBaseUrl = "https://dev.placetopay.com/rest/"
        const val placeToPayLogin = "6dd490faf9cb87a9862245da41170ff2"
        const val placeToPaytranKey = "024h1IlD"

        //payer constants
        const val payerSurname = "Andres"
        const val payerDocumentType = "CC"
        const val payerDocumentNumber = "809284521"

        //payment constants
        const val paymentCurrency = "COP"
        const val paymentReference = "001"
        const val paymentDescription = "Purchase"

        //other constants
        const val transactionRequestIP = "190.85.90.130"
        const val transactionRequestUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"
    }
}