package smr.A

fun main(){
    val array = readLine()!!.split(" ").map { it.toLong() }.toMutableList()
    for (i in 0 until array.lastIndex) {
        var minIdx = i
        for (j in i + 1 until array.size) {
            if (array[j] < array[minIdx]) {
                minIdx = j
            }
        }
        if (minIdx != i) {
            array[i] = array[minIdx].also { array[minIdx] = array[i] }
        }
    }
    println(array[1])
}