import kotlin.collections.Iterator

private val primes : MutableList<Int> = mutableListOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31)

class PrimesIterator : Iterator<Int> {
    private var nextPrimeIndex = 0

    override fun hasNext() = true

    override fun next(): Int {
        if (nextPrimeIndex < primes.size) {
            nextPrimeIndex++
            return primes[nextPrimeIndex - 1]
        }
        var lastPrime = primes.last()
        while (true) {
            lastPrime++
            if (primes.none { lastPrime % it == 0 }) {
                nextPrimeIndex++
                primes.add(lastPrime)
                return lastPrime
            }
        }
    }
}

fun primes(): Iterable<Int> = object : Iterable<Int> {
    override fun iterator(): Iterator<Int> = PrimesIterator()
}

data class Factor(val prime: Int, val power: Int) {
    override fun toString(): String {
        return "$prime^$power"
    }
}

fun factorsOf(nn: Int): List<Factor> {
    val res = mutableListOf<Factor>()
    var n = nn
    primes().takeWhile {
        if (n == 1) {
            return@takeWhile false
        }
        if (n % it == 0) {
            var power = 0
            while (n % it == 0) {
                power++
                n /= it
            }
            res.add(Factor(it, power))
        }
        return@takeWhile true
    }
    return res
}