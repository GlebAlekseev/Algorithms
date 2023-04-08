package toff.graphs

fun main() {
    val (n, m, s, t) = readLine()!!.split(" ").map { it.toInt() }
    val adjacencyList = List(n) { mutableListOf<Edge>() }
    for (i in 1..m) {
        val (b, e, w) = readLine()!!.split(" ").map { it.toInt() }
        adjacencyList[b - 1].add(Edge(e, w))
    }

    val weightList = MutableList(n) {  -1 }
    // base
    weightList[s - 1] = 0

    bfs(adjacencyList, weightList, listOf(Edge(s, 0)), t)
    with(weightList[t - 1]){
        if (this == -1) println("Unreachable") else println(this)
    }
}

fun bfs(adjacencyList: List<List<Edge>>, weightList: MutableList<Int>, baseList: List<Edge>, target: Int) {
    val newBaseList = mutableListOf<Edge>()
    baseList.forEach {
        val neighborsList = adjacencyList[it.targetVertex - 1]
        neighborsList.forEach { neighbor ->
            if (weightList[neighbor.targetVertex - 1] == -1 || weightList[neighbor.targetVertex - 1] > it.cost + neighbor.cost){
                weightList[neighbor.targetVertex - 1] = it.cost + neighbor.cost
                if (neighbor.targetVertex != target){
                    newBaseList.add(Edge(neighbor.targetVertex, neighbor.cost + it.cost))
                }
            }
        }
    }
    if (newBaseList.isEmpty()) return
    bfs(adjacencyList, weightList, newBaseList, target)
}

data class Edge(val targetVertex: Int, var cost: Int)