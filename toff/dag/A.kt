package toff.dag.A

fun main(){
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val adjacencyList = List(n+1) { mutableListOf<Int>() }
    val adjacencyListReversed = List(n+1) { mutableListOf<Int>() }

    for (i in 1..m){
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        adjacencyList[a].add(b)
        adjacencyListReversed[b].add(a)
    }

    val resultTopologySort = mutableListOf<Int>()
    val used = MutableList(n + 1) { false }
    used[0] = true

    for ((index, isUsed) in used.withIndex()){
        if (!isUsed) dfsTopologySort(index, used, adjacencyList, resultTopologySort)
    }

    var counter = 1
    val color = MutableList(n + 1) { 0 }
    for (v in resultTopologySort.reversed()){
        if (color[v] == 0){
            unite(v, counter, color, adjacencyListReversed)
            counter++
        }
    }

    val stationInComponent = MutableList(counter) { 1 }
    stationInComponent[0] = 0
    for (i in 1 until adjacencyList.size){
        for (j in 0 until adjacencyList[i].size){
            if (color[i] != color[adjacencyList[i][j]]) stationInComponent[color[i]] = 0
        }
    }

    println(stationInComponent.count { it == 1 })
}

// Конденсация
fun unite(v: Int, c: Int, color: MutableList<Int>, adjacencyListReversed: List<List<Int>>){
    color[v] = c
    for (u in adjacencyListReversed[v]){
        if (color[u] == 0){
            unite(u, c, color, adjacencyListReversed)
        }
    }
}

fun dfsTopologySort(v: Int, used: MutableList<Boolean>, adjacencyList: List<List<Int>>, result: MutableList<Int>) {
    used[v] = true
    for (u in adjacencyList[v]){
        if (!used[u]) dfsTopologySort(u, used, adjacencyList, result)
    }
    result.add(v)
}