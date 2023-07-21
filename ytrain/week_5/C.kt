package ytrain.week_5

fun main() {
    val n = readln().toInt()
    val h = readln().split(" ").map { it.toInt() }

    for (i in 1 until n) {
        if (h[i - 1] < h[i] && h[i] > h[i + 1]) {
            println(i + 1)
            break
        }
    }
}
