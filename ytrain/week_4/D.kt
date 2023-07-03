package ytrain.week_4


fun main(){
    val v = readln().trim().toInt()
    val matrix = Array(v){ mutableListOf<Int>() }
    val counts = IntArray(v)

    fun dfs(v: Int, parent: Int){
        counts[v] += 1
        for (to in matrix[v]){
            if (to != parent){
                dfs(to, v)
                counts[v] += counts[to]
            }
        }
    }
    repeat(v-1){
        val (a,b) = readln().trim().split(" ").map { it.toInt() - 1 }
        matrix[a].add(b)
        matrix[b].add(a)
    }
    dfs(0, -1)
    counts.joinToString(" ").also { println(it) }
}