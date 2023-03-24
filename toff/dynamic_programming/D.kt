package toff.dynamic_programming

import kotlin.math.min

fun main() {
    val textS = readLine()!!
    val textT = readLine()!!
    val result = damerauLevenshteinDistance(
        textS,
        textT
    )
    println(result)
}

fun damerauLevenshteinDistance(textS: String, textT: String): Int {
    val n = textT.length
    val m = textS.length
    val matrix = Array(n + 1) {
        Array(m + 1) { 0 }
    }
    // base
    for (i in 1..n) {
        matrix[i][0] = i
    }
    for (i in 1..m) {
        matrix[0][i] = i
    }

    for (i in 1..n) {
        for (j in 1..m) {
            matrix[i][j] = min(matrix[i - 1][j], matrix[i][j - 1]) + 1
            if ((textT[i - 1] != textS[j - 1])){
                matrix[i][j] = min(matrix[i][j], matrix[i - 1][j - 1] + 1)
            }else{
                matrix[i][j] = min(matrix[i][j], matrix[i - 1][j - 1])
            }
            if (i > 1 && j > 1 && textT[i - 2] == textS[j - 1] && textS[j - 2] == textT[i - 1]) {
                matrix[i][j] = min(matrix[i][j], matrix[i - 2][j - 2] + 1)
            }
        }
    }
    return matrix[n][m]
}
