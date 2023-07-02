package ytrain.week_4

fun main() {
    val m = readln().trim().toInt()
    val mList = readln().trim().split(" ").map { it.toInt() }.sorted()

    val n = readln().trim().toInt()
    val nList = readln().trim().split(" ").map { it.toInt() }.sorted()

    var mIndex = 0
    var nIndex = 0
    var counter = 0

    while (mIndex < m && nIndex < n) {
        if (nList[nIndex] >= mList[mIndex]) {
            mIndex++
            counter++
        }
        nIndex++
    }
    println(counter)
}
