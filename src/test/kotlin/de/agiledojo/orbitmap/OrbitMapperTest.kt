package de.agiledojo.orbitmap

import io.kotlintest.TestCase
import io.kotlintest.matchers.startWith
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
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

        "2 identical orbits should be counted as one" {
                mapper.numberOfOrbits("A)B\nC)D\nA)B") shouldBe 2
        }

        "single orbits are counted" {
            val numberOfOrbits = mapper.numberOfOrbits("N)M\nB)C\nY)X\nA)B")
            numberOfOrbits shouldBe 5
        }

        "multiple indirect orbits are counted" {
            val numberOfOrbits = mapper.numberOfOrbits("N)M\nA)B\nY)X\nB)C\nB)D")
            numberOfOrbits shouldBe 7
        }

        "transient indirect Orbits are counted" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nB)C\nC)D")
            numberOfOrbits shouldBe 6
        }

        "object with two objects in direct orbit" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nA)C")
            numberOfOrbits shouldBe 2
        }

        "complex example should work" {
            val numberOfOrbits = mapper.numberOfOrbits("COM)B\n" +
                    "B)C\n" +
                    "C)D\n" +
                    "D)E\n" +
                    "E)F\n" +
                    "B)G\n" +
                    "G)H\n" +
                    "D)I\n" +
                    "E)J\n" +
                    "J)K\n" +
                    "K)L")
            numberOfOrbits shouldBe 42
        }

        "objects cannot be in their own orbit" {
            val exception = shouldThrow<IllegalArgumentException> {
                mapper.numberOfOrbits("A)A")
            }
            exception.message should startWith("Objects cannot be in their own orbit")
        }
    }

}