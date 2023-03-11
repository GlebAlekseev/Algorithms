package toff.sorting.merge_sort

val list = listOf(1, 6, 3, 4, 9, 5, 2)

fun main() {
    val result = mergeSort(list)
    println(result)
}

fun mergeSort(list: List<Int>): List<Int> {
    return if (list.size > 1) {
        val middle = list.size / 2
        val leftList = list.slice(0 until middle)
        val rightList = list.slice(middle until list.size)
        merge(mergeSort(leftList), mergeSort(rightList))
    } else list
}

fun merge(leftList: List<Int>, rightList: List<Int>): List<Int> {
    val result = MutableList(leftList.size + rightList.size) { 0 }
    var p1 = 0
    var p2 = 0
    while (p1 < leftList.size && p2 < rightList.size) {
        result[p1 + p2] = if (leftList[p1] < rightList[p2]) leftList[p1].also { p1++ } else rightList[p2].also { p2++ }
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