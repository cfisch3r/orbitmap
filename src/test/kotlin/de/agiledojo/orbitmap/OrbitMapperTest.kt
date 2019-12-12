package de.agiledojo.orbitmap

import io.kotlintest.Description
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class OrbitMapperTest : WordSpec({

    "Sum of orbits" should {
        val mapper = OrbitMapper()
        val numberOfOrbits = mapper.numberOfOrbits("");
        "be 0 for an empty Orbit Map" {
            numberOfOrbits shouldBe 0
        }
    }
}) {
    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        testCase.

    }
}