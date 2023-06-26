package ytrain.week_3

import kotlin.math.min
import kotlin.math.pow

fun main() {
    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val cntLamp = IntArray(k)
    for (i in 0 until k) {
        cntLamp[i] = readln().trim().toInt()
    }
    fun good(m: Int): Boolean {
        var inGarland = 0
        for (i in 0 until k) {
            inGarland += cntLamp[i] / m
        }
        return inGarland >= n
    }

    var l = 0
    var r = (2 * 10.0.pow(9.0)).toInt()
    while (l < r) {
        val m = ((l + r + 1) / 2)
        if (good(m)) {
            l = m
        } else {
            r = m - 1
        }
    }
    println(l)
    var usedLamps = 0
    for (i in 0 until k) {
        for (j in 0 until min(cntLamp[i] / l, n - usedLamps)) {
            println(i + 1)
            usedLamps++
        }
    }
}

