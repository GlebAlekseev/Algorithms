package toff.binary_search.B

fun main() {
    readLine()
    val nArray: List<Long> = readLine()!!.split(" ").map { it.toLong() }
    val kArray: List<Long> = readLine()!!.split(" ").map { it.toLong() }

    for (item in kArray) {
        val result = nArray.myLowerBound(item)
        when (result) {
            null -> println(nArray.last())
            0 -> println(nArray.first())
            else -> {
                val prev = nArray[result - 1]
                if (item - prev <= nArray[result] - item) println(prev) else println(nArray[result])
            }
        }
    }
}

fun List<Long>.myLowerBound(target: Long): Int? {
    if (target > this.last()) return null
    var l = 0
    var r = this.size - 1
    while (l < r) {
        val middle = (l + r) / 2
        if (target > this[middle]) l = middle + 1 else r = middle
    }
    return l
}