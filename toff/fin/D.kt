package toff.fin

import java.util.concurrent.LinkedBlockingQueue

data class Vector(val x: Int, val y: Int)
data class Point(val x: Int, val y: Int) {
    operator fun plus(vector: Vector) = Point(this.x + vector.x, this.y + vector.y)
}

val directions = listOf(
    Vector(0, -1),
    Vector(0, 1),
    Vector(-1, 0),
    Vector(1, 0),
)

fun main() {
    // read
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val matrix = List(n + 1 + 1) { MutableList(m + 1 + 1) { 0 } } // n(x) строк, m(y) столбцов
    for (i in 1..n) {
        matrix[i][0] = 1
        matrix[i][matrix[0].lastIndex] = 1
        val items = readLine()!!.split(" ").map { it.toInt() }
        items.forEachIndexed { j, item ->
            matrix[i][j + 1] = item
        }
    }
    for (i in matrix[0].indices){
        matrix[0][i] = 1
    }
    for (i in matrix[matrix.lastIndex].indices){
        matrix[matrix.lastIndex][i] = 1
    }

    // init
    val matrixWay = List(n + 1) { MutableList(m + 1) { -1 } }
    val queue = LinkedBlockingQueue<Point>()
    // base
    matrixWay[1][1] = 0
    queue.add(Point(1, 1))

    while (true) {
        val currentPoint = queue.remove()
        if (matrix[currentPoint.x][currentPoint.y] == 2) {
            println(matrixWay[currentPoint.x][currentPoint.y])
            break
        }
        directions.forEach { vectorStep ->
            var tmp = currentPoint
            while (true) {
                if (matrix[tmp.x][tmp.y] == 2) break
                if (matrix[tmp.x + vectorStep.x][tmp.y + vectorStep.y] == 1) break
                tmp += vectorStep
            }
            if (matrixWay[tmp.x][tmp.y] == -1) {
                matrixWay[tmp.x][tmp.y] = matrixWay[currentPoint.x][currentPoint.y] + 1
                queue.add(tmp)
            }
        }
    }
}

