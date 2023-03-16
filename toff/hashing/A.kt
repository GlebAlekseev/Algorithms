package toff.hashing.A

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.pow

fun main() {
    val p = (2.0.pow(31) - 1).toLong() // большой модуль
    val k = 31L // произвольное число больше размера алфавита
    val text = readLine()!!
    val m = readLine()!!.toInt()
    val (polynomialHash, powerList) = getPolynomialHash(text, p, k)
    for (i in 1..m) {
        val (a, b, c, d) = readLine()!!.split(" ").map { it.toInt() }
        val result = isMatchSubstrings(polynomialHash, powerList, p, a, b, c, d)
        println(if (result) "Yes" else "No")
    }
}

fun getPolynomialHash(text: String, p: Long, k: Long): Pair<List<Long>, List<Long>> {
    if (text.isEmpty()) throw RuntimeException()
    val powerList = mutableListOf(1L, 1L, k)
    val polynomialHash = mutableListOf(0, (text[0].code - 'A'.code + 1).toLong())
    text.forEachIndexed { index, char ->
        if (index != 0) {
            val s = char.code - 'A'.code + 1
            val hash = (polynomialHash.last() + powerList.last() * s) % p
            val power = (powerList.last() * k) % p
            polynomialHash.add(hash)
            powerList.add(power)
        }
    }
    powerList.removeLast()
    return Pair(polynomialHash, powerList)
}

fun isMatchSubstrings(
    polynomialHash: List<Long>,
    powerList: List<Long>,
    p: Long,
    a: Int,
    b: Int,
    c: Int,
    d: Int
): Boolean {
    val hashAB = ((polynomialHash[b] - polynomialHash[a - 1]) * powerList[powerList.size - b] % p + p) % p
    val hashCD = ((polynomialHash[d] - polynomialHash[c - 1]) * powerList[powerList.size - d] % p + p) % p
    return hashAB == hashCD
}


class Test {
    @org.junit.Test
    fun test1() {
//        println('C'.code)
//        println('A'.code)
//        println('C'.code - 'A'.code)
        println('t'.code - 'A'.code + 1)
        println('r'.code - 'A'.code + 1)
        println('o'.code - 'A'.code + 1)
        println('l'.code - 'A'.code + 1)
//        println((((Int.MAX_VALUE-100)*2)) % 100)
    }

    @org.junit.Test
    fun testGetPolynomialHash() {
//        val result = getPolynomialHash("abcd")
//        println(result)
    }
}


@RunWith(Parameterized::class)
class Test2(
    private val inputString: String,
    private val request: List<Int>,
    private val expected: Boolean
) {
    @Test
    fun test() {
        val p = (2.0.pow(31) - 1).toLong()
        val k = 31L
        val (polynomialHash, powerList) = getPolynomialHash(inputString, p, k)

        val result = isMatchSubstrings(polynomialHash, powerList, p, request[0], request[1], request[2], request[3])
        println(if (result) "Yes" else "No")
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("trololo", listOf(1, 1, 1, 1), true),
                arrayOf("trololo", listOf(1, 7, 1, 7), true),
                arrayOf("trololo", listOf(3, 5, 5, 7), true),
                arrayOf("trololo", listOf(1, 1, 1, 5), false),
                arrayOf("trololo", listOf(7, 7, 7, 7), true),
                arrayOf("trololo", listOf(7, 7, 6, 6), false),
                arrayOf("trololo", listOf(7, 7, 5, 5), true),
                arrayOf(
                    "trololoedfwefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                            "ffffffffffffffffffffffffffffffffffffffffffffffffffffefefwefwefwefwef",
                    listOf(97, 99, 97, 99),
                    true
                ),
            )
        }
    }
}