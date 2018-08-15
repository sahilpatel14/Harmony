package com.ninthsemester.harmony

interface Harmony {

    fun getConnectionState(): State
    fun isConnected(): Boolean
    fun subscribe(connectionListener: ConnectionListener): Int
    fun unsubscribe(subscriptionId: Int)

    enum class State {
        CONNECTED,
        DISCONNECTED
    }

    interface ConnectionListener {
        fun onConnectionChanged(
                currentState: State,
                isConnected: Boolean,
                additionalInfo: Any)
    }

}