package ytrain.week_2

fun main(){
    readln()
    val digits = readln().trim().split(" ").map { it.toInt() }
    val counting = IntArray(digits.maxByOrNull { it }!! + 1)
    digits.forEach { counting[it]++ }

    var maxCount = 0
    for (i in 1..counting.lastIndex){
        val count = counting[i - 1] + counting[i]
        if (count > maxCount) maxCount = count
    }
    println(digits.size - maxCount)
}