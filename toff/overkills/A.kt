package toff.overkills

import kotlin.math.absoluteValue

fun main() {
    val (m, n) = readLine()!!.split(" ").map { it.toInt() }
    val matrix = Array(m) { Array(n) { '-' } }
    val setPoints = SetPoints()
    for (i in 0 until m) {
        for (j in n - 1 downTo 0) {
            if (setPoints.containsIntersectHVD(Point(i, j))) {
                // можно дойти до -
                matrix[i][j] = '+'
            } else {
                // нельзя дойти до +
                setPoints.add(Point(i, j))
            }
        }
    }
    val result = if (matrix[m - 1][0] == '+') 1 else 2
    println(result)
}

class SetPoints {
    private val setPoints = mutableSetOf<Point>()

    fun add(p: Point) {
        setPoints.add(p)
    }

    fun containsIntersectHVD(p: Point): Boolean {
        setPoints.forEach {
            val res = p - it
            val status =
                p.i == it.i || p.j == it.j || (res.i.absoluteValue == res.j.absoluteValue && res.i > 0 && res.j < 0)
            if (status) return true
        }
        return false
    }
}

data class Point(val i: Int, val j: Int) {
    operator fun minus(p: Point): Point {
        return Point(this.i - p.i, this.j - p.j)
    }
}