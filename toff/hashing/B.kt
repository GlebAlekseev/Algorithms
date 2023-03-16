package toff.hashing.B

import kotlin.math.pow

fun main() {
    val text = readLine()!!
    val pattern = readLine()!!
    val patternHash = PolynomialHashing(pattern, text.length)
        .getHashSubstring(1, pattern.length)
    val textPolynomialHashing = PolynomialHashing(text)
    var isFirst = true
    for (i in 1..text.length - pattern.length + 1) {
        val result = patternHash == textPolynomialHashing.getHashSubstring(i, i + pattern.length - 1)
        if (result) {
            if (isFirst) print(i-1).also { isFirst = false } else print(" ${i - 1}")
        }
    }
}

class PolynomialHashing(private val text: String, val n: Int = text.length) {
    var p = (2.0.pow(31) - 1).toLong() // большой модуль
    var k = 31L // произвольное число больше размера алфавита
    var startChar = 'a'
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
