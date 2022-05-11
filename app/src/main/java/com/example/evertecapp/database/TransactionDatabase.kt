package com.example.evertecapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.evertecapp.model.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDAO

    companion object {
        var INSTANCE: TransactionDatabase? = null

        fun getInstance(context: Context): TransactionDatabase? {
            if (INSTANCE == null) {
                synchronized(TransactionDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionDatabase::class.java,
                        "myDBUsers"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}