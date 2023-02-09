package toff.binary_search.B2

fun main() {
    readLine()
    val nArray: List<Long> = readLine()!!.split(" ").map { it.toLong() }
    val kArray: List<Long> = readLine()!!.split(" ").map { it.toLong() }

    for (item in kArray) {
        val result = nArray.getNearestNeighbor(item)
        println(result)
    }
}

fun List<Long>.getNearestNeighbor(target: Long): Long {
    fun isLeftShort(target: Long, left: Long, right: Long): Boolean = target - left <= right - target
    if (target >= this.last()) return this.last()
    if (target <= this.first()) return this.first()
    var l = 0
    var r = this.size - 1
    while (l + 1 < r) {
        val middle = (l + r) / 2
        if (target > this[middle]) l = middle else r = middle
    }
    return if (isLeftShort(target, this[l], this[r])) this[l] else this[r]
}

// Задача B: Рядом