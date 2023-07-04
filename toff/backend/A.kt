package toff_backend

import kotlin.math.pow

fun main(){
    val n = readln().toInt()
    var lastBase = 0L
    for (i in 1..n){
        val base = 2*i - 1
        lastBase += base.toDouble().pow(2).toLong()
        val power = base.toDouble().pow(3).toLong()
        val partResult = if (i == 1) "${power - lastBase}" else " ${power - lastBase}"
        print(partResult)
    }
}