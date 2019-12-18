package de.agiledojo.orbitmap

class OrbitMapper {

    fun numberOfOrbits(orbitmap: String): Int {
        if (orbitmap.isEmpty())
            return 0

        val orbits = OrbitMapParser.orbitsFromMap(orbitmap)

        return orbits.fold(orbits.size,{ orbitcount,orbit-> orbitcount + orbits.indirectOrbits(orbit)})
    }

    private fun List<Orbit>.indirectOrbits(otherOrbit: Orbit): Int {
        var indirectOrbits = 0
        for (orbit in this)
            if (orbit.isCoupledTo(otherOrbit)) indirectOrbits += 1 + this.indirectOrbits(orbit)

        return indirectOrbits
    }

}
