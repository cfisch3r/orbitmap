package de.agiledojo.orbitmap

class Orbit(orbitDefinition: String) {
    val center: String
    val trabant: String

    init {
        val items = orbitDefinition.split(")")
        center = items[0]
        trabant = items[1]
    }
}