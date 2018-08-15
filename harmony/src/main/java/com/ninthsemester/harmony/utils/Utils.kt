package com.ninthsemester.harmony.utils

object Utils {

    /**
     * Gives us a random number in rand [start] to [end] which is not
     * already present in [exclude] array.
     */
    fun generateRandomNumberInRange(start : Int = 0, end: Int = 9999, exclude : Array<Int> = emptyArray()) : Int {

        var randomNumber : Int
        do {
            randomNumber = (start..end).random()
        } while (exclude.contains(randomNumber))

        return randomNumber
    }
}

/**
 * Gives us a random number in rand [start] to [end] which is not
 * already present in [exclude] array.
 */
fun generateRandomNumberInRange(start : Int = 0, end: Int = 9999, exclude : Array<Int> = emptyArray()) : Int {

    var randomNumber : Int
    do {
        randomNumber = (start..end).random()
    } while (exclude.contains(randomNumber))

    return randomNumber
}