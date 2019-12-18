package de.agiledojo.orbitmap

import java.lang.IllegalArgumentException

object OrbitMapParser {

    private val ORBIT_DEFINITION_FORMAT = "(\\w+)\\)(\\w+)"

    internal fun orbitsFromMap(orbitmap: String): List<Orbit> {
        return orbitmap
                .lines()
                .map { line -> line.trim() }
                .distinct()
                .filter { line -> line.isNotEmpty() }
                .map { line -> parseLine(line) }
    }

    private fun parseLine(line: String): Orbit {
        val match = ORBIT_DEFINITION_FORMAT.toRegex().find(line)
        if (match != null)
            return Orbit(match.groupValues[1],match.groupValues[2])
        else
            throw IllegalArgumentException("Invalid orbit definition: $line")
    }
}