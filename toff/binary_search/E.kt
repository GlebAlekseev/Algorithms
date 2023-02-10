package toff.binary_search

import kotlin.math.abs

const val eps = 0.000001

fun main() {
    val coefficients = readLine()!!.split(" ").map { it.toInt() }
    val result = expressionBinarySearch(0.0) { x ->
        coefficients[0] * x * x * x + coefficients[1] * x * x + coefficients[2] * x + coefficients[3]
    }
    println(result)
}

// Задача D. Квадратный корень и квадратный квадрат

fun expressionBinarySearch(target: Double, expression: (value: Double) -> Double): Double {
    if (expression(0.0) == 0.0) return 0.0
    val boundPair = getBound(eps) { nextValue -> expression(nextValue) > target }
    return realBinarySearch(boundPair.first, boundPair.second, target, expression)
}

fun getBound(start: Double, condition: (nextValue: Double) -> Boolean): Pair<Double, Double> {
    var nextValuePositive = start
    var prevValuePositive = nextValuePositive
    var nextValueNegative = start * -1
    var prevValueNegative = nextValueNegative
    while (true) {
        if (condition(nextValuePositive) != condition(prevValuePositive)) {
            return Pair(prevValuePositive, nextValuePositive)
        }
        if (condition(nextValueNegative) != condition(prevValueNegative)) {
            return Pair(nextValueNegative, prevValueNegative)
        }
        prevValuePositive = nextValuePositive
        prevValueNegative = nextValueNegative
        nextValuePositive *= 2
        nextValueNegative *= 2
    }
}

fun realBinarySearch(l: Double, r: Double,target: Double, expression: (value: Double) -> Double): Double {
    val startOverTarget = expression(l) > 0 && expression(r) < 0
    var l = l
    var r = r
    while (abs(abs(r) - abs(l)) > eps) {
        val middle = (l + r) / 2
        if (!startOverTarget && target >= expression(middle) || startOverTarget && target <= expression(middle)) l = middle else r = middle
    }
    return l
}