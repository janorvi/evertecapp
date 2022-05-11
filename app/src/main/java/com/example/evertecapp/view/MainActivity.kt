package com.example.evertecapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.evertecapp.R
import com.example.evertecapp.utils.Commons

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nonce = Commons.getRandom()
        val seed = Commons.getCurrentDateFormat()
        val nonceBase64 = Commons.getBase64(nonce.toByteArray())
        val tranKeyBase64 = Commons.getBase64(Commons.getSHA256(nonce + seed + "024h1IlD"))
        Log.i("auth_nonce", nonce)
        Log.i("auth_seed", seed)
        Log.i("auth_nonceBase64", nonceBase64);
        Log.i("auth_tranKeyBase64", tranKeyBase64)
    }
}