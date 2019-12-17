package de.agiledojo.orbitmap

class OrbitMapper {

    fun numberOfOrbits(orbitmap: String): Int {
        if (orbitmap.isEmpty())
            return 0

        val orbits = orbitsFromMap(orbitmap)

        return orbits.fold(orbits.size,{ orbitcount,orbit-> orbitcount + orbits.indirectOrbits(orbit)})
    }

    private fun orbitsFromMap(orbitmap: String): List<Orbit> {
        return orbitmap
                .split("\n")
                .distinct()
                .map { line -> Orbit(line) }
    }

    private fun List<Orbit>.indirectOrbits(otherOrbit: Orbit): Int {
        var indirectOrbits = 0
        for (orbit in this)
            if (orbit.isCoupledTo(otherOrbit)) indirectOrbits += 1 + this.indirectOrbits(orbit)

        return indirectOrbits
    }

}
