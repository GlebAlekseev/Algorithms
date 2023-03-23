package toff.dynamic_programming

fun main(){
    val n = readLine()!!.toInt()
    val result = countSafeContainers(n)
    println(result)
}

fun countSafeContainers(n: Int): Int {
    // base
    var countA = 1
    var countBC = 2

    for (i in 2..n){
        val count = (countA + countBC) * 3 - countA
        countA = countBC
        countBC = count - countBC
    }
    return countA + countBC
}