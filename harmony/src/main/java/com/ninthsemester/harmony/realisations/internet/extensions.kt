package com.ninthsemester.harmony.realisations.internet

import android.content.Context
import android.net.ConnectivityManager
import com.ninthsemester.harmony.Harmony

fun getCurrentNetworkInfoState(context: Context) : Harmony.ConnectionInfo {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //  if it is null then we don't have any active network connection.
    //  return false or whatever.
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
            ?: return Harmony.ConnectionInfo(isConnected = false)

    val status = if(activeNetworkInfo.isAvailable) {
        Harmony.State.CONNECTED
    } else {
        Harmony.State.DISCONNECTED
    }

    return Harmony.ConnectionInfo(
            activeNetworkInfo.isConnected,
            status,
            activeNetworkInfo.type)
}


fun isConnectedToNetwork(context: Context): Boolean {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return false
    return activeNetworkInfo.isConnected
}