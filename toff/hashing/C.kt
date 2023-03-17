package toff.hashing.C

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.pow

fun main() {
    val pattern = readLine()!!
    val text = readLine()!!
    val patternPolynomialHashing = PolynomialHashing(pattern, text.length)
    val textPolynomialHashing = PolynomialHashing(text)
    val result = mutableListOf<Int>()
    for (i in 1..text.length - pattern.length + 1) {
        val isEqual = isEqualSubString(textPolynomialHashing, patternPolynomialHashing, i, i + pattern.length - 1)
        if (isEqual){
            result.add(i)
        }
    }
    println(result.size)
    println(result.joinToString(" "))
}

fun isEqualSubString(textPolynomialHashing: PolynomialHashing, patternPolynomialHashing: PolynomialHashing, l: Int, r: Int): Boolean {
    // пройти все комбинации с одной ошибкой и без ошибок, сравнить хеши, если хоть раз верно, то выйти с truer
    val l1 = l
    var r1 = l-1
    var l2 = l+1
    val r2 = r
    while (r1 <= r2){
        if (r1 == r2) l2--
        val leftPart = if (l1 > r1) true else textPolynomialHashing.getHashSubstring(l1, r1) == patternPolynomialHashing.getHashSubstring(l1 - l + 1, r1 - l + 1)
        val rightPart = textPolynomialHashing.getHashSubstring(l2, r2) == patternPolynomialHashing.getHashSubstring(l2 - l + 1, r2 - l + 1)
        if (leftPart && rightPart) return true
        l2++
        r1++
    }
    return false
}

class PolynomialHashing(private val text: String, val n: Int = text.length) {
    var p = (2.0.pow(31) - 1).toLong() // большой модуль
    var k = 31L // произвольное число больше размера алфавита
    var startChar = 'A'
    private val powerList = mutableListOf(1L, 1L, k)
    private val polynomialHash = mutableListOf(0, (text[0].code - startChar.code + 1).toLong())
    init {
        assert(text.isNotEmpty())
        text.forEachIndexed { index, char ->
            if (index != 0) {
                val s = char.code - startChar.code + 1
                val hash = (polynomialHash.last() + powerList.last() * s) % p
                val power = (powerList.last() * k) % p
                polynomialHash.add(hash)
                powerList.add(power)
            }
        }
        powerList.removeLast()
        while (n >= powerList.size){
            val power = (powerList.last() * k) % p
            powerList.add(power)
        }
    }

    fun getHashSubstring(l: Int, r: Int): Long = ((polynomialHash[r] - polynomialHash[l - 1]) * powerList[powerList.size - r] % p + p) % p
}


@RunWith(Parameterized::class)
class Test2(
    private val pattern: String,
    private val text: String,
    private val expected: List<Int>
) {
    @Test
    fun test() {
        println("text=$text pattern=$pattern")
        val patternPolynomialHashing = PolynomialHashing(pattern, text.length)
        val textPolynomialHashing = PolynomialHashing(text)
        val result = mutableListOf<Int>()
        for (i in 1..text.length - pattern.length + 1) {
            val isEqual = isEqualSubString(textPolynomialHashing, patternPolynomialHashing, i, i + pattern.length - 1)
            if (isEqual){
                result.add(i)
            }
        }
        println(result.size)
        println(result.joinToString(" "))

        Assert.assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("aaaa", "Caaabdaaaa", listOf(1,2,6,7)),
            )
        }
    }
}