package ytrain

fun main() {
    readLine()
    val timeList = readLine()!!.trim().split(" ").map { time ->
        val (hours, minutes) = time.trim().split(":").map { it.toInt() }
        return@map hours * 60 + minutes
    }.sorted()

    if (timeList.size == 1) {
        println(24 * 60)
        return
    }
    val diffList = mutableListOf<Int>()
    for (i in 1 until timeList.size) {
        diffList.add(kotlin.math.abs(timeList[i] - timeList[i - 1]))
    }
    diffList.add(24 * 60 - (timeList.lastOrNull() ?: 0) + (timeList.firstOrNull() ?: 0))
    val min = diffList.minOf { it }

    println(min)
}