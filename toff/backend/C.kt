package toff_backend

import kotlin.math.absoluteValue

fun main() {
    val (n, m, q) = readln().split(" ").map { it.toInt() }
    val matrix = mutableListOf<List<Int>>()
    val transposedMatrix = List(m) { mutableListOf<Int>() }
    for (i in 1..n) {
        val dangerLevels = readln().split(" ").map { it.toInt() }.onEachIndexed { j, value ->
            transposedMatrix[j].add(value)
        }
        matrix.add(dangerLevels)
    }

    val sortedHorizontals = matrix.map { it.sorted() }
    val sortedVerticals = transposedMatrix.map { it.sorted() }

    for (i in 1..q) {
        val (x, y, k) = readln().split(" ").map { it.toInt() }
        val currentDangerValue = matrix[x-1][y-1]
        val availableRangeOfDanger = (currentDangerValue-k)..(currentDangerValue+k)
        val startHorizontal = sortedHorizontals[x - 1].upperBound ( availableRangeOfDanger.first )
        val endHorizontal = sortedHorizontals[x - 1].lowerBound ( availableRangeOfDanger.last )
        val startVertical = sortedVerticals[x - 1].upperBound ( availableRangeOfDanger.first )
        val endVertical = sortedVerticals[x - 1].lowerBound ( availableRangeOfDanger.last )
        if (endHorizontal == null || endVertical == null || startHorizontal == null || startVertical == null) {
            println(0)
            continue
        }
        val result = (endHorizontal - startHorizontal).absoluteValue + (endVertical - startVertical).absoluteValue
        println(result)
    }
}



fun List<Int>.upperBound(target: Int): Int? {
    if (target > this.last()) return null
    var l = 0
    var r = this.size - 1
    while (l < r) {
        val middle = (l + r) / 2
        if (target > this[middle]) l = middle + 1 else r = middle
    }
    return l
}

fun List<Int>.lowerBound(target: Int): Int? {
    if (target < this.first()) return null
    var l = 0
    var r = this.size
    while (l < r) {
        val middle = (l + r) / 2
        if (target >= this[middle]) l = middle + 1 else r = middle
    }
    return l - 1
}