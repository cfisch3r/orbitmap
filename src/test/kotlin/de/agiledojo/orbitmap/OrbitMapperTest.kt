package de.agiledojo.orbitmap

import io.kotlintest.Description
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class OrbitMapperTest : WordSpec()  {

    private lateinit var mapper: OrbitMapper

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        mapper = OrbitMapper()
    }

    init {

        "Sum of orbits" should {
            val numberOfOrbits = mapper.numberOfOrbits("")

            "be 0 for an empty Orbit Map" {
                numberOfOrbits shouldBe 0
            }
        }

    }

}