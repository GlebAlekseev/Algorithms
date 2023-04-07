package toff.graphs.B

fun main(){
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val adjacencyList = List(n) { mutableListOf<Int>() }
    for (_i in 1..m) {
        val (i, j) = readLine()!!.split(" ").map { it.toInt() }
        adjacencyList[i - 1].add(j)
    }
    val used = Array(n) { 0 }
    var result = false
    for (v in 0 until n){
        result = dfs(adjacencyList, used, v)
        if (result) break
    }
    println(if (result) 1 else 0)
}

fun dfs(
    adjacencyList: List<MutableList<Int>>,
    used: Array<Int>,
    v: Int,
): Boolean {
    if (used[v] == 1) return true
    if (used[v] == 2) return false
    used[v] = 1
    for (u in adjacencyList[v]) {
        val res = dfs(adjacencyList, used, u - 1)
        if (res) return true
    }
    used[v] = 2
    return false
}