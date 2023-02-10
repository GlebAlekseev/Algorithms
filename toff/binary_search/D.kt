package toff.binary_search.D

import kotlin.math.sqrt

const val eps = 0.0000001

fun main() {
    val target = readLine()!!.toDouble()
    val result = expressionBinarySearch(target) { value ->
        value * value + sqrt(value + 1)
    }
    println(result)
}

// Задача D. Квадратный корень и квадратный квадрат

fun expressionBinarySearch(target: Double, expression: (value: Double) -> Double): Double {
    // [-0.999999;-0.000001] U [0.000001;inf)
    fun isNegative(): Boolean = target in 1.0..expression.invoke(-0.9273199999999667)
    return if (isNegative()) {
        realBinarySearch(-0.6180339999905077, -0.9273199999999667) { middle -> target >= expression(middle) }
    } else {
        val boundPair = positiveBound(eps) { nextValue -> expression(nextValue) < target }
        realBinarySearch(boundPair.first, boundPair.second) { middle -> target >= expression(middle) }
    }
}

fun positiveBound(start: Double, condition: (nextValue: Double) -> Boolean): Pair<Double, Double> {
    var nextValue = start
    var prevValue = start
    while (condition(nextValue)) {
        prevValue = nextValue
        nextValue *= 2
    }
    return Pair(prevValue, nextValue)
}

fun realBinarySearch(l: Double, r: Double, condition: (value: Double) -> Boolean): Double {
    var l = l
    var r = r
    while (r - l > eps) {
        val middle = (l + r) / 2
        if (condition(middle)) l = middle else r = middle
    }
    return l
}