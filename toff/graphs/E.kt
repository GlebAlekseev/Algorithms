package toff.graphs.E

fun main() {
    val (n, m, s, t) = readLine()!!.split(" ").map { it.toInt() }
    val edgeList = mutableListOf<Edge>()
    for (i in 1..m) {
        val (b, e, w) = readLine()!!.split(" ").map { it.toInt() }
        edgeList.add(Edge(b, e, w))
    }
    val result = solveFordBellman(edgeList, n)
    println(result)
    if (s == t) {
        println(0)
    }else {
        if (result[t - 1] == maxCost) println("Unreachable") else println(result[t - 1])
    }
}

data class Edge(val src: Int, val dst: Int, val cost: Int)

const val maxCost = Int.MAX_VALUE

fun solveFordBellman(edgeList: List<Edge>, n: Int): List<Int>{
    val list = MutableList(n) { maxCost }
    list[0] = 0
    while (true){
        var isFinish = false
        for (j in edgeList.indices){
            if (list[edgeList[j].src - 1] != maxCost){
                if(list[edgeList[j].dst - 1] > list[edgeList[j].src - 1] + edgeList[j].cost){
                    list[edgeList[j].dst - 1] = list[edgeList[j].src - 1] + edgeList[j].cost
                    isFinish = true
                }
            }
        }
        if (!isFinish) break
    }
    return list
}

// bad
//3 3 3 1
//1 2 12
//3 2 -122
//1 3 134