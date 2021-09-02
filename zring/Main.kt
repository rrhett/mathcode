fun main() {
    // Want to do:
    // Iterate over all primes, and find out if the prime can be written in the form
    // a + b, where a and b can be factored such that the only primes with odd
    // powers are in a given set, and to find the smallest number that doesn't
    // match this.
    computeSequence()
}

fun computeSequence() {
    val primesToAdd = primes().iterator()
    val primeSet = mutableSetOf<Int>()
    primes().takeWhile {
        // TODO: needs proof that this is an increasing sequence, or restart from 2
        // A002223 seems to be increasing...
        while (!isRepresentable(it, primeSet)) {
            println("$it could not be represented with $primeSet")
            primeSet.add(primesToAdd.next())
        }
        return@takeWhile it < 1000000
    }
}

fun printRepresentations(p: Int, adjoinedRoots: Set<Int>) {
    (1..(p/2)).filter { isRepresentation(p, it, adjoinedRoots) }
        .forEach { println("$p = $it + ${p-it} = ${factorsOf(it)} + ${factorsOf(p-it)}") }
}

fun isRepresentation(p: Int, n: Int, adjoinedRoots: Set<Int>): Boolean =
    isValid(adjoinedRoots, n) && isValid(adjoinedRoots,p-n)

fun isRepresentable(p: Int, adjoinedRoots: Set<Int>): Boolean =
    (1..(p/2)).any { isRepresentation(p, it, adjoinedRoots) }

fun isValid(adjoinedRoots: Set<Int>, n: Int): Boolean {
    val factors = factorsOf(n)
    return factors.all {
        it.power % 2 == 0 || adjoinedRoots.contains(it.prime)
    }
}