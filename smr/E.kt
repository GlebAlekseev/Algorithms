package smr

fun main() {
    val n = readLine()!!.toInt()
    val arrayA = readLine()!!.split(" ").map { it.toInt() }
    val arrayB = readLine()!!.split(" ").map { it.toInt() }

    var sum = 0L
    var maxLength = -1
    val map = HashMap<Long, Int>()

    for (i in 0 until n) {
        sum += arrayA[i] - arrayB[i]

        if (sum == 0L) maxLength = i

        if (map.containsKey(sum)) {
            maxLength = maxOf(maxLength, i - map[sum]!! - 1)
        } else {
            map[sum] = i
        }
    }
    println(maxLength)
}