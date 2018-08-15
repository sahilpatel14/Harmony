package com.ninthsemester.harmony.base

import com.ninthsemester.harmony.Harmony
import com.ninthsemester.harmony.utils.generateRandomNumberInRange


/**
 * When the first request to subscribe is made, we would want the
 * child class to start listening to connection changes. Not before that.
 *
 * The subscriber id would be returned by the child class, or this class ?
 * This class would keep track of all the subscriptions.
 *
 */
abstract class HarmonyBase : Harmony {

    private val subscriptionIds : MutableList<Int> = mutableListOf()

    abstract fun startListeningToConnectionChanges()
    abstract fun stopListeningToConnectionChanges()

    abstract fun registerSubscriber(subscriptionId: Int, connectionListener: Harmony.ConnectionListener) : Boolean
    abstract fun unregisterSubscriber(subscriptionId: Int) : Boolean

    override fun subscribe(connectionListener: Harmony.ConnectionListener): Int {
        val subscriptionId = getSubscriptionId()

        if (subscriptionIds.isEmpty()) {
            startListeningToConnectionChanges()
        }

        val isRegistered = registerSubscriber(subscriptionId, connectionListener)

        return if (isRegistered) {
            subscriptionIds.add(subscriptionId)
            subscriptionId
        } else {
            NOT_SUBSCRIBED
        }

    }

    override fun unsubscribe(subscriptionId: Int) {
        //  Invalid subscription id
        if (!subscriptionIds.contains(subscriptionId)) {
            return
        }

        val isUnregistered = unregisterSubscriber(subscriptionId)
        if (isUnregistered) {
            subscriptionIds.remove(subscriptionId)
        }
    }

    private fun getSubscriptionId () : Int{
        return generateRandomNumberInRange(SUBSCRIPTION_ID_START, SUBSCRIPTION_ID_END, subscriptionIds.toTypedArray())
    }

    companion object {
        const val SUBSCRIPTION_ID_START = 10000
        const val SUBSCRIPTION_ID_END = 99999

        const val NOT_SUBSCRIBED = -1
    }
}