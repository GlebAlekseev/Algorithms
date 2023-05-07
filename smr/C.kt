package smr.C

fun main() {
    val k = readLine()!!.toInt()
    val n = readLine()!!.toInt()
    val arrayAi = readLine()!!.split(" ").map { it.toInt() }
    val sumArray = mutableListOf(0L)
    val shlSumArray = mutableListOf(0L)

    // O(n) префиксные суммы
    arrayAi.forEachIndexed { j, ai ->
        val i = j + 1
        sumArray.add(sumArray[i - 1] + ai * i)
        shlSumArray.add(shlSumArray[i - 1] + ai * (i + 1))
    }

    // пройтись по всем позиция и найти максимум
    var maxSum = 0L

    for (i in 1..n + 1) {
        val sum = sumArray[i - 1] + k * i + (shlSumArray[n] - shlSumArray[i - 1])
        if (sum > maxSum) {
            maxSum = sum
        }
    }

    println(maxSum - sumArray.last())
}