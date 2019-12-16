package de.agiledojo.orbitmap

import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class OrbitMapperTest : StringSpec()  {

    private lateinit var mapper: OrbitMapper

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        mapper = OrbitMapper()
    }

    init {

        "Sum of orbits should be 0 for an empty Orbit Map" {
            mapper.numberOfOrbits("") shouldBe 0
        }

        "Sum of orbits should be 1 for an Orbit Map with 1 orbit" {
            mapper.numberOfOrbits("A)B") shouldBe 1
        }

        "Sum of orbits should be 2 for an Orbit Map with 2 independent Orbits" {
            mapper.numberOfOrbits("A)B\nC)D") shouldBe 2
        }

        "2 identical Orbits should be counted as one" {
                mapper.numberOfOrbits("A)B\nC)D\nA)B") shouldBe 2
        }

        "indirect Orbits are also counted" {
            val numberOfOrbits = mapper.numberOfOrbits("N)M\nB)C\nY)X\nA)B")
            numberOfOrbits shouldBe 5
        }

        "multiple indirect Orbits are counted" {
            val numberOfOrbits = mapper.numberOfOrbits("N)M\nA)B\nY)X\nB)C\nB)D")
            numberOfOrbits shouldBe 7
        }

        "transient indirect Orbits are counted" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nB)C\nC)D")
            numberOfOrbits shouldBe 6
        }

        "planet with separated two planets in orbit" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nA)C")
            numberOfOrbits shouldBe 2
        }

        "!Sum of orbits should be 3 for an Orbit Map with 2 independent and 1 indirect Orbits" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nB)C")
            numberOfOrbits shouldBe 3
        }

        "Mapper should work with Windows Line Breaks" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\r\nC)D")
            numberOfOrbits shouldBe 2
        }
    }

}