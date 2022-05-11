package com.example.evertecapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.evertecapp.model.Transaction

@Dao
interface TransactionDAO {

    @Insert
    suspend fun insert(transaction: Transaction)
}