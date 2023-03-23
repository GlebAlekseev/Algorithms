package toff.dynamic_programming

import kotlin.math.min

fun main(){
    readLine()
    val costs = readLine()!!.split(" ").map { it.toInt() }
    val result = leastSum(costs)
    println(result)
}

fun leastSum(list: List<Int>): Int {
    var a = list[0] // i - 2
    var b = list.getOrNull(1) ?: return a // i - 1
    if (list.size == 2) return b

    for (i in 2 until list.size){
        val min = min(a, b)
        a = b
        b = min + list[i]
    }
    return b
}
