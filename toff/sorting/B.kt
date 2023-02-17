package toff.sorting.B

//var counter = 0
fun main() {
    val n = readLine()!!.toInt()
    val result = antiQuickSort(n)
    result.forEachIndexed { index, l -> if (index == result.size - 1) println("$l") else print("$l ") }
}

fun antiQuickSort(n: Int): List<Int> {
    val result = (1..n).toMutableList()
    var i = 3
    while (n >= i) {
        val lastIndex = i - 1
        val qIndex = (0 + lastIndex) / 2
        result[qIndex] = result[lastIndex].also { result[lastIndex] = result[qIndex] }
        i++
    }
    return result
}


//fun quickSort(list: MutableList<Int>) {
//    quickSort(list, 0, list.size - 1)
//}

//fun quickSort(list: MutableList<Int>, left: Int, right: Int) {
//    println("l=$left r=$right list=${list}")
//    if (right <= left) return
//    val q = list[(left + right) / 2]
//    var i = left
//    var j = right
//    while (i <= j) {
//        while (run {
//                println("\tlist[i] < q : ${list[i] < q}, где i=$i list[i]=${list[i]} q=$q}")
//                ++counter
//            } > 0 && list[i] < q) i++;
//        while (run {
//                println("\tq < list[j] : ${q < list[j]}, где j=$j list[j]=${list[j]} q=$q}")
//                ++counter
//            } > 0 && q < list[j]) j--
//        if (i <= j) {
//            list[i] = list[j].also { list[j] = list[i] }
//            println("\t\tlist=${list} swap i:$i & j:$j")
//            i++
//            j--
//        }
//    }
//    quickSort(list, left, j)
//    quickSort(list, i, right)
//}