package de.agiledojo.orbitmap

import java.lang.IllegalArgumentException

internal class Orbit(private val center: String, private val trabant: String) {

    init  {
        if (center == trabant) throw IllegalArgumentException("Objects cannot be in their own orbit")
    }

    fun isCoupledTo(other: Orbit): Boolean {
        return this.center == other.trabant
    }

}