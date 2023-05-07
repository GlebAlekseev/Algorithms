package smr.D

fun main(){
    readLine()
    val arrayAi = readLine()!!.split(" ").map { it.toInt() }
    var indexOne = -1
    for (i in arrayAi.indices){
        if (arrayAi[i] == 1) indexOne = i
    }

    val count = if(indexOne == -1){
        val range = 1..arrayAi.size
        (range.first + range.last) * range.last / 2
    }else {
        val left = 1..indexOne
        val right = 1 until arrayAi.size - indexOne
        (left.first + left.last) * left.last / 2 + (right.first + right.last) * right.last / 2
    }
    println(count)
}