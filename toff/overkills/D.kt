package toff.overkills

import kotlin.math.min

fun main(){
    val n = readLine()!!.toInt()
    val array = MutableList(n) { 0 }
    partitioning(array, 0, n, n)
}

fun partitioning(array: MutableList<Int>, currentPosition: Int, currentValue: Int, maxValue: Int){
    for (index in 1..min(currentValue, maxValue)){
        array[currentPosition] = index
        if (currentValue - index == 0) {
            println(array.subList(0, currentPosition + 1).joinToString(" "))
        } else {
            partitioning(array, currentPosition + 1, currentValue - index, index)
        }
    }
}