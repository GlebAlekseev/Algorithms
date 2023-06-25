package ytrain.week_3

fun main() {
    readln()
    val sumPrefix = readln()
        .trim()
        .split(" ")
        .map { it.toInt() }
        .map { if (it > 0) 1 else 0 }
        .runningFold(0) { acc, item ->
            acc + item
        }
    val q = readln().trim().toInt()
    for (i in 1..q) {
        val (l, r) = readln().trim().split(" ").map { it.toInt() }
        println(sumPrefix[r] - sumPrefix[l - 1])
    }
}