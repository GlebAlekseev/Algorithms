package toff.sorting.C

import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputStringArray = mutableListOf<String>()
    while (true) {
        inputStringArray.add(readLine() ?: break)
    }
    val result = mergeSort(inputStringArray)
    for (i in result.size - 1 downTo 0 step 1) {
        if (i == 0) println(result[i]) else print(result[i])
    }
}

// Задача C. Число

fun mergeSort(list: List<String>): List<String> {
    return if (list.size > 1) {
        val middle = list.size / 2
        val leftList = list.slice(0 until middle)
        val rightList = list.slice(middle until list.size)
        merge(mergeSort(leftList), mergeSort(rightList))
    } else list
}

fun merge(leftList: List<String>, rightList: List<String>): List<String> {
    fun compare(a: String, b: String): Boolean {
        val minSize = min(a.length, b.length)
        val maxSize = max(a.length, b.length)
        var i = 0
        while (i < minSize) {
            if (a[i] != b[i]) return a[i] < b[i]
            i++
        }
        if (maxSize != minSize) {
            return compare(a+b,b+a)
        }
        return a[i - 1] < b[i - 1]
    }

    val result = MutableList(leftList.size + rightList.size) { "" }
    var p1 = 0
    var p2 = 0
    while (p1 < leftList.size && p2 < rightList.size) {
        result[p1 + p2] =
            if (compare(leftList[p1], rightList[p2])) leftList[p1].also { p1++ } else rightList[p2].also { p2++ }
    }
    if (p1 == leftList.size) {
        while (p2 < rightList.size) {
            result[p1 + p2] = rightList[p2].also { p2++ }
        }
    } else if (p2 == rightList.size) {
        while (p1 < leftList.size) {
            result[p1 + p2] = leftList[p1].also { p1++ }
        }
    }
    return result
}