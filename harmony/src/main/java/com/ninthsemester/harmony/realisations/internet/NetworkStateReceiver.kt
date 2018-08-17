package com.ninthsemester.harmony.realisations.internet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log



class NetworkStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {
            Log.v(TAG, "action: " + it.action)
            Log.v(TAG, "component: " + it.component)

            val extras = intent.extras
            extras?.keySet()?.forEach {
                Log.v(TAG, "key [" + it + "]: " +
                        extras.get(it))
            } ?: Log.v(TAG, "no extras")
        }
    }

    companion object {
        const val TAG = "Network State : "
    }
}