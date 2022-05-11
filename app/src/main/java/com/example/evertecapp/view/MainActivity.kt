package com.example.evertecapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.evertecapp.R
import com.example.evertecapp.utils.Commons
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val transactionInfoFragment = TransactionInfoFragment()
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val nonce = Commons.getRandom()
        val seed = Commons.getCurrentDateFormat()
        val nonceBase64 = Commons.getBase64(nonce.toByteArray())
        val tranKeyBase64 = Commons.getBase64(Commons.getSHA256(nonce + seed + "024h1IlD"))
        Log.i("auth_nonce", nonce)
        Log.i("auth_seed", seed)
        Log.i("auth_nonceBase64", nonceBase64);
        Log.i("auth_tranKeyBase64", tranKeyBase64)*/
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.transaction_info_item -> replaceFragment(transactionInfoFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit();
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}