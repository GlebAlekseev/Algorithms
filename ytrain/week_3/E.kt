package ytrain.week_3

import java.math.BigDecimal
import kotlin.math.abs

fun main() {
    val list =
        readLine()!!.trim().split(" ")
    var n = list[0].toInt()
    val k = list[1].toLong()
    n += 1
    val ai =
        ("0 " + readln().trim()).trim().split(" ").map { it.toInt() }.sortedDescending()
    var prefixSum = LongArray(n + 1)

    for (i in 1..n) {
        prefixSum[i] = prefixSum[i - 1] + ai[i - 1]
    }
    prefixSum = prefixSum.copyOfRange(1, prefixSum.size)


    var r =
        maxOf(ai.first() * 2, abs(ai.last() * 2))
    var l = -r


    while (r > l) {
        val middle = (l + r + 1) / 2
        val result = checkLimit(middle, k, n, ai, prefixSum)

        if (result.first) {
            l = middle
        } else {
            r = middle - 1
        }
    }
    println(checkLimit(l, k, n, ai, prefixSum).second)
}

fun checkLimit(
    middle: Int,
    k: Long,
    n: Int,
    ai: List<Int>,
    prefixSum: LongArray
): Pair<Boolean, BigDecimal> {
    var total = BigDecimal(0)
    var pairs = 0L
    var j = 1

    while (j < n - 1 && (ai[0] + ai[j] >= middle)) {
        j += 1
    }

    for (i in 0 until n) {
        if (i >= j) {
            break
        }
        while (j > i && ai[i] + ai[j] < middle) {
            j -= 1
        }
        total += BigDecimal(prefixSum[j]) - BigDecimal(prefixSum[i]) + BigDecimal(ai[i]) * BigDecimal((j - i))
        pairs += (j - i)
    }

    return if (pairs >= k) {
        Pair(true, total - BigDecimal((pairs - k) * middle))
    } else {
        Pair(false, BigDecimal(0))
    }
}
