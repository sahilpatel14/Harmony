package com.ninthsemester.harmony.realisations.internet

import android.annotation.TargetApi
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import com.ninthsemester.harmony.Harmony
import com.ninthsemester.harmony.base.HarmonyBase

class HarmonyImpl(private val context: Context) : HarmonyBase() {


    private val subscribers : MutableMap<Int, Harmony.ConnectionListener> = mutableMapOf()
    private val connectivityManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkStateReceiver = NetworkStateReceiver()

    private val connectionChangeListener = @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback() {


        override fun onLost(network: Network?) {
            super.onLost(network)
            subscribers.forEach {
                it.value.onConnectionChanged(Harmony.ConnectionInfo(false, Harmony.State.DISCONNECTED), false)
            }

            Log.d(TAG, "onLost : "+network.toString())
        }

        override fun onUnavailable() {
            super.onUnavailable()
            val info = getCurrentNetworkInfoState(context)
            subscribers.forEach {

                it.value.onConnectionChanged(info, true)
            }
            Log.d(TAG, "onUnAvailable : ")
        }

        override fun onAvailable(network: Network?) {
            super.onAvailable(network)
            val info = getCurrentNetworkInfoState(context)
            subscribers.forEach {
                it.value.onConnectionChanged(info, true)
            }

            Log.d(TAG, "onAvailable : "+network.toString())
        }


    }

    override fun startListeningToConnectionChanges() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            context.registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        } else {
            connectivityManager.registerDefaultNetworkCallback(connectionChangeListener)
        }
    }

    override fun stopListeningToConnectionChanges() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            context.unregisterReceiver(networkStateReceiver)
        } else {
            connectivityManager.unregisterNetworkCallback(connectionChangeListener)
        }
    }

    override fun registerSubscriber(subscriptionId: Int, connectionListener: Harmony.ConnectionListener): Boolean {
        subscribers[subscriptionId] = connectionListener
        return true
    }

    override fun unregisterSubscriber(subscriptionId: Int): Boolean {
        if (subscribers.keys.contains(subscriptionId)) {
            subscribers.remove(subscriptionId)
            return true
        }
        return false
    }

    override fun getConnectionInfo(): Harmony.ConnectionInfo {
     return getCurrentNetworkInfoState(context)
    }


    override fun isConnected(): Boolean {
        return isConnectedToNetwork(context)
    }

    companion object {
        const val TAG = "Harmony Impl : "
    }
}