package com.ninthsemester.harmony

interface Harmony {

    fun getConnectionInfo(): ConnectionInfo
    fun isConnected(): Boolean
    fun subscribe(connectionListener: ConnectionListener): Int
    fun unsubscribe(subscriptionId: Int)

    enum class State {
        CONNECTED,
        DISCONNECTED
    }

    enum class Connection {
        TYPE_WIFI,
        TYPE_4G,
        TYPE_3G
    }

    interface ConnectionListener {
        fun onConnectionChanged(
                currentState: State,
                isConnected: Boolean,
                additionalInfo: Any? = null)
    }

    data class ConnectionInfo (
            val isConnected: Boolean,
            val state: State ?= null,
            val connectionType: Int ?= null)

}