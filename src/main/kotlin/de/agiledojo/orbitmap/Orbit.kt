package de.agiledojo.orbitmap

import java.lang.IllegalArgumentException

internal class Orbit(orbitDefinition: String) {
    val center: String
    val trabant: String

    init {
        val items = orbitDefinition.split(")")
        validate(items)
        center = items[0]
        trabant = items[1]
    }

    private fun validate(items: List<String>) {
        if (items.size != 2) throw IllegalArgumentException("Invalid orbit definition")
        if (items[0] == items[1]) throw IllegalArgumentException("Objects cannot be in their own orbit")
    }
}