package de.agiledojo.orbitmap

class OrbitMapper {
    fun numberOfOrbits(orbitmap: String): Int {
        if (orbitmap.isEmpty())
            return 0

        val lines = orbitmap.split("\n")
        val distinctLines = lines.distinct()
        return distinctLines.size
    }

}
