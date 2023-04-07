package toff.graphs.A

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val adjacencyList = List(n) { mutableListOf<Int>() }
    for (_i in 1..m) {
        val (i, j) = readLine()!!.split(" ").map { it.toInt() }
        adjacencyList[j - 1].add(i)
        adjacencyList[i - 1].add(j)
    }
    val component = Array(n) { -1 }
    val result = mutableListOf<MutableList<Int>>()
    var num = 0
    for (v in 0 until n) {
        if (component[v] == -1) {
            dfs(adjacencyList, component, result, v, num)
            num++
        }
    }
    println(result.size)
    result.forEach {
        println(it.size)
        println(it.sorted().joinToString(" "))
    }
}

fun dfs(
    adjacencyList: List<MutableList<Int>>,
    component: Array<Int>,
    result: MutableList<MutableList<Int>>,
    v: Int,
    num: Int
) {
    component[v] = num
    if (result.size <= num) result.add(mutableListOf(v + 1)) else result[num].add(v + 1)
    for (u in adjacencyList[v]) {
        if (component[u - 1] == -1) {
            dfs(adjacencyList, component, result, u - 1, num)
        }
    }
}