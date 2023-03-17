package toff.hashing.D

import org.junit.Test
import kotlin.math.pow

fun main() {
    val text = readLine()!!
    val textPolynomialHashing = PolynomialHashing(text)
    val reverseTextPolynomialHashing = PolynomialHashing(text.reversed())
    var counter = 0
    for (i in 1..text.length) {
        for (j in i downTo 1) {
            //j..i
            val result = isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, j, i)
            if (result) counter++
        }
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

class Test1 {
//    @Test
//    fun test1(){
//        val text = "abcdefgh"
//        val reversedText = text.reversed()
//        println("\t$text\n\t$reversedText")
//        val l = 0
//        val r = 4
//        println(text.substring(l,r+1))
//        println(reversedText.substring(text.length - r - 1, text.length - l))
//    }
//    @Test
//    fun test1(){
//        val text = "aaa"
//        val reversedText = text.reversed()
//        val n = text.length
//        val mid = (l+r)/2
//    val leftHash = textPolynomialHashing.getHashSubstring(l, mid)
//    val rightReversedHash = if (r - l % 2 == 1){
//        println("n - mid - 1 = ${n - mid - 1} | n - l=${n - l}")
//        reversedTextPolynomialHashing.getHashSubstring(n - mid - 1+1, n - l+1)
//    }else {
//        println("n - mid = ${n - mid} | n - l=${n - l}")
//        reversedTextPolynomialHashing.getHashSubstring(n - mid+1, n - l+1)
//    }
//    }

    @Test
    fun test2() {

        val stringBuilder = StringBuilder()
        repeat(10000){
            stringBuilder.append("abcdefghiq")
        }

//        val text = "abcdefrtykulk"
        val text = stringBuilder.toString()
        val textPolynomialHashing = PolynomialHashing(text)
        val reverseTextPolynomialHashing = PolynomialHashing(text.reversed())
        var counter = 0
        // обойти припрфиксы и суффиксы, процверить палиндромность, инкрементировать

        for (i in 1..text.length) {
            for (j in i downTo 1) {
                //j..i
                val result = isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, j, i)
                println("result=$result i=$i j=$j")
                if (result) counter++
            }
        }
//        val result = isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, 1, 4)
//        println(">>>>>>result=$result")
//
//        val result = isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, 2, 3)
//        println(">>>>>>result=$result")

        println(counter)
    }

}