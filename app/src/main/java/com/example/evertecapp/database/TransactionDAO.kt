package com.example.evertecapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.evertecapp.model.Transaction

@Dao
interface TransactionDAO {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("select * from transactions_table")
    suspend fun getAllTransactions(): List<Transaction>
}