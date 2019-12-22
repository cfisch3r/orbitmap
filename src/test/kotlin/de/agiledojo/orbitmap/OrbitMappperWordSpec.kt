package de.agiledojo.orbitmap

import io.kotlintest.TestCase
import io.kotlintest.matchers.startWith
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

class OrbitMapperWordSpec : WordSpec()  {

    private lateinit var mapper: OrbitMapper

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        mapper = OrbitMapper()
    }

    init {

        "number of orbits" should {

            "be 0 for an empty Orbit Map" {
                mapper.numberOfOrbits("") shouldBe 0
            }

            "be 1 for an Orbit Map with 1 orbit" {
                mapper.numberOfOrbits("A)B") shouldBe 1
            }

            "be 2 for an Orbit Map with 2 independent Orbits" {
                mapper.numberOfOrbits("""
                    A)B
                    C)D
                """) shouldBe 2
            }

            "count 2 identical orbits as one" {
                mapper.numberOfOrbits("""
                    A)B
                    C)D
                    A)B
                """) shouldBe 2
            }

            "count single indirect orbit" {
                mapper.numberOfOrbits("""
                    N)M
                    B)C
                    Y)X
                    A)B
                """) shouldBe 5
            }

            "count multiple indirect orbits" {
                mapper.numberOfOrbits("""
                    N)M
                    A)B
                    Y)X
                    B)C
                    B)D
                """) shouldBe 7
            }

            "count transient indirect orbits" {
                mapper.numberOfOrbits("""
                    A)B
                    B)C
                    C)D
                """) shouldBe 6
            }

            "count object with two objects in direct orbit" {
                mapper.numberOfOrbits("""
                    A)B
                    A)C
                """) shouldBe 2
            }

            "work for a complex example" {
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
        }

        "Orbit definitions " should {

            "not have the same object as center and satelite" {
                val exception = shouldThrow<IllegalArgumentException> {
                    mapper.numberOfOrbits("A)A")
                }
                exception.message should startWith("Objects cannot be in their own orbit")
            }

            "throw an exception when invalid" {
                val exception = shouldThrow<IllegalArgumentException> {
                    mapper.numberOfOrbits("xyz")
                }
                exception.message should startWith("Invalid orbit definition")
            }
        }

        "Orbit map lines " should {

            "be ignored when empty" {
                mapper.numberOfOrbits("\nA)B") shouldBe 1
            }

            "be ignored when the have blanks only." {
                mapper.numberOfOrbits("  \nA)B") shouldBe 1
            }
        }

    }

}