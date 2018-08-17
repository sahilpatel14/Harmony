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
        TYPE_3G,
        NO_IDEA
    }

    interface ConnectionListener {
        fun onConnectionChanged(
                connectionInfo: ConnectionInfo,
                isConnected: Boolean,
                additionalInfo: Any? = null)
    }

    data class ConnectionInfo (
            val isConnected: Boolean,
            val state: State ?= null,
            val connectionType: Connection ?= null)

}