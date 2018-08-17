package com.ninthsemester.dope

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ninthsemester.harmony.Harmony
import com.ninthsemester.harmony.realisations.internet.HarmonyImpl

class MainActivity : AppCompatActivity() {

    private val harmony by lazy {
        HarmonyImpl(this)
    }

    private var subscriptionId : Int? = null
    private val connectionChangeCListener = object: Harmony.ConnectionListener {

        override fun onConnectionChanged(connectionInfo: Harmony.ConnectionInfo, isConnected: Boolean, additionalInfo: Any?) {
            Log.d(TAG, "Harmony says connection has changed.")
            Log.d(TAG, "Connection Type : ${connectionInfo.connectionType}")
            Log.d(TAG, "Is Connected ? : ${connectionInfo.isConnected}")
            Log.d(TAG, "Connection State : ${connectionInfo.state}")        }
    }

    override fun onStart() {
        super.onStart()
        harmony.subscribe(connectionChangeCListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptionId?.let {
            harmony.unregisterSubscriber(subscriptionId!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Show us something")
        testingCurrentConnectionStatus()
        testingCurrentConnectionState()
    }

    private fun testingCurrentConnectionStatus() {
        Log.d(TAG, "Asking the module if the device is connected.")
        Log.d(TAG, "Is Device connected? "+harmony.isConnected())
    }

    private fun testingCurrentConnectionState() {

        val connectionInfo = harmony.getConnectionInfo()

        Log.d(TAG, "Asking the module for additional information about the connection")
        Log.d(TAG, "Connection Type : ${connectionInfo.connectionType}")
        Log.d(TAG, "Is Connected ? : ${connectionInfo.isConnected}")
        Log.d(TAG, "Connection State : ${connectionInfo.state}")
    }



    companion object {
        const val TAG: String = "MAIN : "
    }
}
