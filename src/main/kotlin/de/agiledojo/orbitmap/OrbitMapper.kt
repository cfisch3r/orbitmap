package de.agiledojo.orbitmap

class OrbitMapper {
    fun numberOfOrbits(orbitmap: String): Int {
        if (orbitmap.isEmpty())
            return 0

        val lines = orbitmap.split("\n")
        val distinctLines = lines.distinct()
        val orbits = distinctLines.map { line -> Orbit(line) }
        var indirectOrbits = 0

        for (orbit in orbits)
            for (i in 1 until orbits.size)
                if (orbit.trabant == orbits[i].center)
                    indirectOrbits = 1
        return distinctLines.size + indirectOrbits
    }

}
