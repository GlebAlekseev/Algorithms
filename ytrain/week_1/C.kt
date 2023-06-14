package ytrain

fun main() {
    readLine()
    val costs = readLine()!!.split(" ").map { it.toInt() }
    var min = 0
    var max = 0
    var currentMin = 0

    for (i in costs.indices) {
        if (costs[max] * costs[currentMin] < costs[min] * costs[i]) {
            min = currentMin
            max = i
        }
        if (costs[i] < costs[currentMin]) {
            currentMin = i
        }
    }
    if (min == max) println("0 0") else println("${min + 1} ${max + 1}")
}