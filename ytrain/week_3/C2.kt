package ytrain.week_3

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val nAndK = scanner.nextLine().split(" ").map { it.toInt() }
    val aK: MutableList<Pair<Int, Int>> = mutableListOf()

    for (i in 1..nAndK[1]) {
        val a = scanner.nextInt()
        aK.add(Pair(i, a))
    }

    aK.sortBy { it.second }
    val sum = aK.sumBy { it.second }

    var l = 1
    var r = sum / nAndK[1] + 1

    while (r > l) {
        val mid = (l + r + 1) / 2
        var sumSameColors = 0

        for (a in aK) {
            val sameColors = a.second / mid
            sumSameColors += sameColors
        }

        if (sumSameColors >= nAndK[0]) {
            l = mid
        } else {
            r -= 1
        }
    }

    println(l)
}
