package ytrain.week_2

import kotlin.math.pow

private var p = (2.0.pow(31) - 1).toLong()
private var k = 31L
private val powerList = mutableListOf(1L, 1L, k)

fun main(){
    for (i in 1..100){
        val power = (powerList.last() * k) % p
        powerList.add(power)
    }

    val dictionary = readln().trim().split(" ")
    val words = readln().trim().split(" ").toMutableList()
    // подсчитать хеши для dictionary и words за O(d) и O(w)
    val dictionaryPolynomialHashing = dictionary.map { PolynomialHashing(it) }
    val wordsPolynomialHashing = words.map { PolynomialHashing(it) }
    // буртфорс за O(dw)
    for (d in dictionaryPolynomialHashing.indices){
        for (w in wordsPolynomialHashing.indices){
            val dictionaryWordPolynomialHashing = dictionaryPolynomialHashing[d]
            val wordPolynomialHashing = wordsPolynomialHashing[w]
            if (wordPolynomialHashing.containsPrefix(dictionaryWordPolynomialHashing)){
                if (words[w] > dictionary[d]) words[w] = dictionary[d]
            }
        }
    }
    println(words.joinToString(" "))
}

fun PolynomialHashing.containsPrefix(polynomialHashing: PolynomialHashing): Boolean{
    if (this.textLength < polynomialHashing.textLength) return false
    val prefix1 = polynomialHashing.getHashSubstring(1,polynomialHashing.textLength)
    val prefix2 = this.getHashSubstring(1, polynomialHashing.textLength)
    return prefix1 == prefix2
}

class PolynomialHashing(private val text: String) {
    val textLength get() = text.length
    private var startChar = 'a'
    private val polynomialHash = mutableListOf(0, (text[0].code - startChar.code + 1).toLong())

    init {
        text.forEachIndexed { index, char ->
            if (index != 0) {
                val s = char.code - startChar.code + 1
                val hash = (polynomialHash.last() + powerList[index + 1] * s) % p
                polynomialHash.add(hash)
            }
        }
    }

    fun getHashSubstring(l: Int, r: Int): Long =
        ((polynomialHash[r] - polynomialHash[l - 1]) * powerList[powerList.size - r] % p + p) % p
}