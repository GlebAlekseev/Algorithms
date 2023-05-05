package toff.approximation

import kotlin.math.exp
import kotlin.random.Random

fun main() {
    val n = readLine()!!.toInt()
    val rand = Random(System.currentTimeMillis())
    var result = (0 until n).toList()
    var t = 1.0
    var currentMax = f(result)
    var i = 0
    while (i < 100000 && currentMax < n){
        t *= 0.99
        val tmpList = result.toMutableList()
        val r1 = rand.nextInt(0,n)
        val r2 = rand.nextInt(0,n)
        tmpList[r1] = tmpList[r2].also { tmpList[r2] = tmpList[r1] }
        val value = f(tmpList)
        if (value > currentMax || exp((value - currentMax) / t) > rand.nextInt(0,2)){
            result = tmpList
            currentMax = value
        }
        i++
    }
    println(result.map{ it + 1 }.joinToString(" "))

}

fun f(rearrangement: List<Int>): Int {
    var counter = 0
    val diagonalIsUsedRightTop = MutableList(2*rearrangement.size + 1) { false }
    val diagonalIsUsedRightBottom = MutableList(2*rearrangement.size + 1) { false }
    for (i in rearrangement.indices) {
        val x = rearrangement[i] + 1
        val y = i + 1
        val rightTopIndex = x + y
        val rightBottomIndex = rearrangement.size - x + 1 + y
        if (!diagonalIsUsedRightTop[rightTopIndex] && !diagonalIsUsedRightBottom[rightBottomIndex]) {
            counter++
        }
        diagonalIsUsedRightTop[rightTopIndex] = true
        diagonalIsUsedRightBottom[rightBottomIndex] = true
    }
    return counter
}