package ytrain.week_4

fun main(){
    val (l,n,m) = readln().trim().split(" ").map { it.toInt() }
    val prefixSum = IntArray(l + 1)

    repeat(n) {
        val (left, right) = readln().trim().split(" ").map { it.toInt() }
        prefixSum[left - 1] += 1
        prefixSum[right] -= 1
    }

    for (i in 1 until l) {
        prefixSum[i] += prefixSum[i - 1]
    }

    repeat(m) {
        val r = readln().trim().toInt()
        println(prefixSum[r - 1])
    }
}