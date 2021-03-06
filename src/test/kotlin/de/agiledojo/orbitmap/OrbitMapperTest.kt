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

        "number of orbits should be 0 for an empty Orbit Map" {
            mapper.numberOfOrbits("") shouldBe 0
        }

        "number of orbits should be 1 for an Orbit Map with 1 orbit" {
            mapper.numberOfOrbits("A)B") shouldBe 1
        }

        "number of orbits should be 2 for an Orbit Map with 2 independent Orbits" {
            mapper.numberOfOrbits("""
                A)B
                C)D
            """) shouldBe 2
        }

        "2 identical orbits should be counted as one" {
            mapper.numberOfOrbits("""
                A)B
                C)D
                A)B
            """) shouldBe 2
        }

        "single indirect orbit is counted" {
            mapper.numberOfOrbits("""
                N)M
                B)C
                Y)X
                A)B
            """) shouldBe 5
        }

        "multiple indirect orbits are counted" {
            mapper.numberOfOrbits("""
                N)M
                A)B
                Y)X
                B)C
                B)D
            """) shouldBe 7
        }

        "transient indirect orbits are counted" {
            mapper.numberOfOrbits("""
                A)B
                B)C
                C)D
            """) shouldBe 6
        }

        "object with two objects in direct orbit" {
            mapper.numberOfOrbits("""
                A)B
                A)C
            """) shouldBe 2
        }

        "complex example should work" {
            mapper.numberOfOrbits("""
                        COM)B
                        B)C
                        C)D
                        D)E
                        E)F
                        B)G
                        G)H
                        D)I
                        E)J
                        J)K
                        K)L
                    """) shouldBe 42
        }

        "objects cannot be in their own orbit" {
            val exception = shouldThrow<IllegalArgumentException> {
                mapper.numberOfOrbits("A)A")
            }
            exception.message should startWith("Objects cannot be in their own orbit")
        }

        "invalid orbit definitions are not accepted" {
            val exception = shouldThrow<IllegalArgumentException> {
                mapper.numberOfOrbits("xyz")
            }
            exception.message should startWith("Invalid orbit definition")
        }

        "empty lines in orbit maps are ignored." {
            mapper.numberOfOrbits("\nA)B") shouldBe 1
        }

        "lines with blanks in orbit maps are ignored." {
            mapper.numberOfOrbits("  \nA)B") shouldBe 1
        }
    }

}