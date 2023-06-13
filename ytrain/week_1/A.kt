package ytrain

fun main() {
    val n = readLine()!!.toLong()
    val (start, end) = getBoundaries {
        f(it) < n
    }
    val result = (start..end).lowerBound(n)
    println(result)
}

fun f(n: Long) = ((1 + n) * n) / 2

fun getBoundaries(expression: (Long) -> Boolean): Pair<Long, Long> {
    var start = 1L
    var end = start
    while (expression(end)) {
        start = end
        end *= 2
    }
    return Pair(start, end)
}

fun LongRange.lowerBound(target: Long): Long {
    var l = this.first
    var r = this.last
    while (l < r) {
        val middle = (l + r) / 2
        if (target > f(middle)) l = middle + 1L else r = middle

    }
    return if (f(l) <= target) l else l - 1
}