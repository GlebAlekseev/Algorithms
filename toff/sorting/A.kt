package toff.sorting.A

fun main() {
    readLine()
    val aArray: List<Long> = readLine()!!.split(" ").map { it.toLong() }
    val (result, inversions) = mergeSort(aArray)
    println(inversions)
    result.forEachIndexed { index, l -> if (index == result.size - 1) println("$l") else print("$l ") }
}

// Задача A: Сортировка слиянием с приколом
fun mergeSort(list: List<Long>): Pair<List<Long>, ULong> {
    return if (list.size > 1) {
        val middle = list.size / 2
        val leftList = list.slice(0 until middle)
        val rightList = list.slice(middle until list.size)
        merge(mergeSort(leftList), mergeSort(rightList))
    } else Pair(list, 0UL)
}

fun merge(leftList: Pair<List<Long>, ULong>, rightList: Pair<List<Long>, ULong>): Pair<List<Long>, ULong> {
    var inversions = leftList.second + rightList.second
    val result = MutableList(leftList.first.size + rightList.first.size) { 0L }
    var p1 = 0
    var p2 = 0
    while (p1 < leftList.first.size && p2 < rightList.first.size) {
        result[p1 + p2] =
            if (leftList.first[p1] < rightList.first[p2]) leftList.first[p1].also { p1++ } else rightList.first[p2].also {
                inversions += (leftList.first.size - p1).toUInt()
                p2++
            }
    }
    if (p1 == leftList.first.size) {
        while (p2 < rightList.first.size) {
            result[p1 + p2] = rightList.first[p2].also { p2++ }
        }
    } else if (p2 == rightList.first.size) {
        while (p1 < leftList.first.size) {
            result[p1 + p2] = leftList.first[p1].also { p1++ }
        }
    }
    return Pair(result, inversions)
}