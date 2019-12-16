package de.agiledojo.orbitmap

class OrbitMapper {
    fun numberOfOrbits(orbitmap: String): Int {
        if (orbitmap.isEmpty())
            return 0

        val lines = orbitmap.split("\n")
        val distinctLines = lines.distinct()
        val orbits = distinctLines.map { line -> Orbit(line) }
        var indirectOrbits = 0

        for (orbit in orbits) {
            indirectOrbits += countIndirectOrbits(orbits, orbit)
        }
        return distinctLines.size + indirectOrbits
    }

    private fun countIndirectOrbits(orbits: List<Orbit>, orbit: Orbit): Int {
        var indirectOrbits = 0
        for (otherOrbit in orbits) {
            if (orbit.trabant == otherOrbit.center) {
                indirectOrbits++
                indirectOrbits += countIndirectOrbits(orbits,otherOrbit)
            }
        }

        return indirectOrbits
    }

}
