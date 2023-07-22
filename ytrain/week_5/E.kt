package ytrain.week_5

fun main() {
    val n = readln().toInt()
    val h = readln().split(" ").map { it.toInt() }

    var maxIndex = 0
    for (i in 0 until n) {
        if (h[i] > h[maxIndex]) {
            maxIndex = i
        }
    }

    var ans = 0L
    var nowMax = h[0]
    for (i in 0 until maxIndex) {
        if (h[i] > nowMax) {
            nowMax = h[i]
        } else {
            ans += nowMax - h[i]
        }
    }

    nowMax = h[n - 1]
    for (i in n - 1 downTo maxIndex) {
        if (h[i] > nowMax) {
            nowMax = h[i]
        } else {
            ans += nowMax - h[i]
        }
    }

    println(ans)
}
