package toff.hashing.D2

import kotlin.math.pow

// O(nlogn)

fun main(){
    val text = readLine()!!
    val textPolynomialHashing = PolynomialHashing(text)
    val reverseTextPolynomialHashing = PolynomialHashing(text.reversed())
    var counter = 0L
    for (i in text.indices){
        val maxDistance =  if (i < text.length/2) i else text.length - i - 1
        var l = 0
        var r = maxDistance
        while (l <= r){
            val middle = (l + r) / 2
            if (isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, i - middle + 1, i + middle + 1)) l = middle + 1 else r = middle - 1
        }
        counter += r + 1L
    }
    for (i in 1 until text.length){
        val maxDistance =  if (i <= text.length/2) i - 1 else text.length - i - 1
        var l = 0
        var r = maxDistance
        while (l <= r){
            val middle = (l + r) / 2
            if (isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, i - 1 - middle + 1, i + middle + 1)) l = middle + 1 else r = middle - 1
        }
        counter += r + 1L
    }
    println(counter)
}

fun isPalindrome(
    textPolynomialHashing: PolynomialHashing,
    reversedTextPolynomialHashing: PolynomialHashing,
    l: Int,
    r: Int
): Boolean {
    if (r == l) return true
    val n = textPolynomialHashing.n
    val mid = (l + r) / 2
    val leftHash = textPolynomialHashing.getHashSubstring(l, mid)
    val rightReversedHash = if ((r - l + 1) % 2 == 1) {
        reversedTextPolynomialHashing.getHashSubstring(n - r + 1, n - mid + 1)
    } else {
        reversedTextPolynomialHashing.getHashSubstring(n - r + 1, n - mid)
    }
    return leftHash == rightReversedHash
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
        while (n >= powerList.size) {
            val power = (powerList.last() * k) % p
            powerList.add(power)
        }
    }

    fun getHashSubstring(l: Int, r: Int): Long =
        ((polynomialHash[r] - polynomialHash[l - 1]) * powerList[powerList.size - r] % p + p) % p
}