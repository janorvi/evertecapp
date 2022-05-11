package com.example.evertecapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
class Transaction(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "transaction_reference") var reference: String,
    @ColumnInfo(name = "transaction_amount") var amount: String,
    @ColumnInfo(name = "transaction_payer_name") var payerName: String,
    @ColumnInfo(name = "transaction_payer_email") var payerEmail: String,
    @ColumnInfo(name = "transaction_payer_phone") var payerPhone: String,
    @ColumnInfo(name = "transaction_card_number") var cardNumber: String,
    @ColumnInfo(name = "transaction_status") var status: String){
}