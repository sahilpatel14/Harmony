package com.ninthsemester.dope

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ninthsemester.harmony.Harmony
import com.ninthsemester.harmony.realisations.internet.HarmonyImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Show us something")
        HarmonyImpl(this).subscribe(object : Harmony.ConnectionListener {
            override fun onConnectionChanged(currentState: Harmony.State, isConnected: Boolean, additionalInfo: Any?) {

            }
        })
    }

    companion object {
        const val TAG : String = "MAIN : ";
    }
}
