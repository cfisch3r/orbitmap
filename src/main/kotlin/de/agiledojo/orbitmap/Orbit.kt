package de.agiledojo.orbitmap

import java.lang.IllegalArgumentException

internal class Orbit(orbitDefinition: String) {
    val center: String
    val trabant: String

    init {
        val items = orbitDefinition.split(")")
        center = items[0]
        trabant = items[1]
        if (center == trabant) throw IllegalArgumentException("Objects cannot be in their own orbit")
    }
}