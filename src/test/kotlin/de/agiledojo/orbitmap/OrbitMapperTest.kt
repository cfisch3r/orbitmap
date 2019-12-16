package de.agiledojo.orbitmap

import io.kotlintest.TestCase
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class OrbitMapperTest : StringSpec()  {

    private lateinit var mapper: OrbitMapper

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        mapper = OrbitMapper()
    }

    init {

        "Sum of orbits should be 0 for an empty Orbit Map" {
            val numberOfOrbits = mapper.numberOfOrbits("")
            numberOfOrbits shouldBe 0
        }

        "Sum of orbits should be 1 for an Orbit Map with 1 orbit" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B")
            numberOfOrbits shouldBe 1
        }

        "Sum of orbits should be 2 for an Orbit Map with 2 independent Orbits" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B\nC)D")
            numberOfOrbits shouldBe 2
        }

        "2 identical Orbits should be counted as one" {
                val numberOfOrbits = mapper.numberOfOrbits("A)B\nC)D\nA)B")
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