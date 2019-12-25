# Orbit Map Kata

This Kata is inspired by a [puzzle](https://adventofcode.com/2019/day/6) from the [Advent of Code site](https://adventofcode.com/).

You have to implement a function which calculates the sum of orbits defined in a map. This map contains a list of orbital relationships between celestial objects. For example:
```$xslt
A)B
```
This map entry specifies that object B is in the orbit of object A.

Besides these direct relationships the calculation also has to include the transient orbits, for example:
```$xslt
A)B
B)C
```
Object C is in an indirect orbit of object A, so the sum of orbits is 3.

I took the opportunity to check out [KotlinTest](https://github.com/kotlintest/kotlintest) as Unit Test Framework and was quite impressed by its features. As you can see in the [Test Class Code](src/test/kotlin/de/agiledojo/orbitmap/OrbitMapperTest.kt) it comes with a very expressive Assertion Library. Additionally You can use strings as test names to improve the readability of your specification even more. And last but not least you have the choice  between different [Testing Styles](https://github.com/kotlintest/kotlintest/blob/master/doc/styles.md)   . For example the WordSpec base class gives you an additional nesting level if needed. You can see this approach in [OrbitMapperWordSpec](src/test/kotlin/de/agiledojo/orbitmap/OrbitMappperWordSpec.kt).

