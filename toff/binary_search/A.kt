package toff.binary_search

fun main(){
    readLine()
    val nArray: List<Int> = readLine()!!.split(" ").map { it.toInt() }
    val kArray: List<Int>  = readLine()!!.split(" ").map { it.toInt() }

    for (item in kArray){
        val result = nArray.myBinarySearch(item)
        if (result == null) println("NO") else println("YES")
    }
}

fun List<Int>.myBinarySearch(target: Int): Int? {
    var l = 0
    var r = this.size - 1
    while (l <= r){
        val middle = (l + r) / 2
        if (target == this[middle]) return middle
        if (target > this[middle]) l = middle + 1 else r = middle - 1
    }
    return null
}