package toff.dynamic_programming

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val result = countWaysRD(n, m)
    println(result)
}

fun countWaysRD(n: Int, m: Int): Int {
    val matrix = Array(n + 2) { Array(m + 2) { 0 } }
    // base
    matrix[1][1] = 1
    fun countWays(i: Int, j: Int): Int {
        if (i < 1 || j < 1 || j > m || i > n) return 0
        if (matrix[i][j] == 0) {
            matrix[i][j] =  countWays(i - 2, j - 1) + countWays(i - 2, j + 1) +
                            countWays(i - 1, j - 2) + countWays(i + 1, j - 2)
        }
        return matrix[i][j]
    }
    return countWays(n, m)
}