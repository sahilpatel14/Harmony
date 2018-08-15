package com.ninthsemester.dope

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ninthsemester.harmony.realisations.internet.getCurrentNetworkInfoState

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Show us something")
        val response = getCurrentNetworkInfoState(this)
        Log.d(TAG, response)
    }

    companion object {
        const val TAG : String = "MAIN : ";
    }
}
