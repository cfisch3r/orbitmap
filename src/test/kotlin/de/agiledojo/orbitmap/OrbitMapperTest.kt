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
            val numberOfOrbits = mapper.numberOfOrbits("")
            numberOfOrbits shouldBe 0
        }

        "Sum of orbits should be 1 for an Orbit Map with two planets" {
            val numberOfOrbits = mapper.numberOfOrbits("A)B")
            numberOfOrbits shouldBe 1
        }
    }

}