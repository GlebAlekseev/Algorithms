package toff.overkills.C2

import kotlin.math.pow

fun main(){
    val t = readLine()!!.toInt()
    val maxValue = 10.0.pow(18).toULong()
    val fibonacciMultipliers = getFibonacciMultipliers(maxValue)
    for (i in 1..t) {
        val target = readLine()!!.toULong()
        val result = getNumberWaysRepresentAsProductFibonacciNumbers(0, fibonacciMultipliers, target)
        println(result)
    }
}

fun getFibonacciMultipliers(maxValue: ULong): List<ULong> {
    val result = mutableListOf<ULong>()
    result.add(1UL)
    result.add(1UL)

    var i = 2
    while (true) {
        val newValue = result[i - 2] + result[i - 1]
        if (newValue > maxValue) break
        result.add(newValue)
        i++
    }
    return result.subList(2, result.size)
}

fun getNumberWaysRepresentAsProductFibonacciNumbers(index: Int, multipliers: List<ULong>, target: ULong): ULong {
    if	(target == 1UL) return 1UL
    if	(index > multipliers.lastIndex || target < multipliers[index]) return 0UL

    var result: ULong = getNumberWaysRepresentAsProductFibonacciNumbers(index + 1, multipliers, target)
    val currentMultiplier = multipliers[index]
    if	(target % currentMultiplier == 0UL)
        result += getNumberWaysRepresentAsProductFibonacciNumbers(index, multipliers, target/currentMultiplier)
    return result
}