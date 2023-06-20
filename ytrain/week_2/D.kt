package ytrain.week_2

fun main(){
    readln()
    val digits = readln().trim().split(" ").map { it.toInt() }.sorted() // nlogn
    var maxCounter = 0
    var maxValue = Int.MIN_VALUE
    var tmpCounter = 0
    var tmpValue = Int.MIN_VALUE
    for (i in digits.indices){
        if (tmpValue != digits[i]) tmpCounter = 0
        tmpValue = digits[i]
        tmpCounter++
        if (tmpCounter > maxCounter){
            maxCounter = tmpCounter
            maxValue = tmpValue
        }
    }
    println(maxValue)
}

