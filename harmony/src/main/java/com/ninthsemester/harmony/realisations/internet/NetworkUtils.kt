package com.ninthsemester.harmony.realisations.internet

import android.content.Context
import android.net.ConnectivityManager

fun getCurrentNetworkInfoState(context: Context) : String {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //  if it is null then we don't have any active network connection.
    //  return false or whatever.
    val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return "Not connected to any network."
    return activeNetworkInfo.toString()
}