package toff.graphs

fun main() {
    val n = readLine()!!.toInt()
    val (x1, y1) = readLine()!!.split(" ").map { it.toInt() }
    val (x2, y2) = readLine()!!.split(" ").map { it.toInt() }

    val adjacencyList = List(n * n) { mutableListOf<Vertex>() }
    val base = listOf(Vertex(x1, y1))
    val used = List(n){MutableList(n) { false } }
    used[x1 - 1][y1 - 1] = true
    val result = makeAdjacencyList(adjacencyList, used, base, n, Vertex(x2, y2))

    var tmpResult = result
    val steps = mutableListOf<Vertex>()
    while (true){
        steps.add(tmpResult)
        tmpResult = tmpResult.parent ?: break
    }
    println(steps.size - 1)
    steps.reversed().forEach {
        println("${it.x} ${it.y}")
    }
}

fun makeAdjacencyList(adjacencyList: List<MutableList<Vertex>>, used: List<MutableList<Boolean>>, baseList: List<Vertex>, n: Int, target: Vertex): Vertex {
    val newBaseList = mutableListOf<Vertex>()
    baseList.forEach {
        val neighborsList = it.getNeighborsList(n)
        neighborsList.forEach { neighbor ->
            if (!used[neighbor.x - 1][neighbor.y - 1]){
                used[neighbor.x - 1][neighbor.y - 1] = true
                adjacencyList[it.getNumber(n) - 1].add(neighbor)
                newBaseList.add(neighbor)
                neighbor.parent = it
                if (neighbor == target) return  neighbor
            }
        }
    }
    return makeAdjacencyList(adjacencyList, used, newBaseList, n, target)
}

fun Vertex.getNeighborsList(n: Int): List<Vertex> {
    val list = mutableListOf<Vertex>()
    list.add(Vertex(x + 2, y + 1))
    list.add(Vertex(x + 2, y - 1))
    list.add(Vertex(x - 2, y + 1))
    list.add(Vertex(x - 2, y - 1))
    list.add(Vertex(x + 1, y + 2))
    list.add(Vertex(x - 1, y + 2))
    list.add(Vertex(x + 1, y - 2))
    list.add(Vertex(x - 1, y - 2))
    return list.filter { it.x in 1..n && it.y in 1..n }.distinct()
}

data class Vertex(val x: Int, val y: Int) {
    var parent: Vertex? = null
    fun getNumber(n: Int): Int = n * (y - 1) + x
}