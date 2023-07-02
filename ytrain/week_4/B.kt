package ytrain.week_4

fun main(){
    val n = readln().trim().toInt()
    val boundaries = mutableListOf<Triple<Int,Int, Int>>()
    repeat(n){
        val (l,r) = readln().trim().split(" ").map { it.toInt() }
        boundaries.add(Triple(l, r, it))
    }
    boundaries.sortWith(compareBy({ it.first }, { it.second }))
    var prevGoodIndex = 0
    for (i in 1 until boundaries.size){
        if (boundaries[i].second > boundaries[prevGoodIndex].second){
            boundaries[i] = Triple(maxOf(boundaries[i].first, boundaries[prevGoodIndex].second), boundaries[i].second, boundaries[i].third)
            prevGoodIndex = i
        } else {
            boundaries[i] = Triple(-1, -1, boundaries[i].third)
        }
    }
    boundaries.sortedBy { it.third }.onEach {
        println("${it.first} ${it.second}")
    }
}
