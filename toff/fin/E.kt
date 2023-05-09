package toff.fin.E

import kotlin.math.min

var primaryKeyId = 0
const val CUSTOM_MAX_VALUE = Long.MAX_VALUE / 100000

fun main(){
    val dp = MutableList(101){CUSTOM_MAX_VALUE}
    val matrixAdjacency = List(101){ mutableListOf<Int>() }

    val ingredientNumber = HashMap<String, Int>()
    // 1. Получение нужных инградиентов
    readLine()
    val ingredientList = readLine()!!.split(" ")
    ingredientList.forEach {
        ingredientNumber.put(it, ++primaryKeyId)
    }


    // 2. Получение инградиентов, возможных к покупке

    val m = readLine()!!.toInt()
    for (i in 1..m){
        val (ingredientName, cost) = readLine()!!.split(" ")
        ingredientNumber.putIfAbsent(ingredientName, ++primaryKeyId)
        dp[ingredientNumber[ingredientName]!!] = cost.toLong()
    }


    // 3. Получение возможных крафтов, count, target, items
    // Инициализация id, чтобы внести в матрицу смежности
    // Пополнение матрицы смежности, из каждого ид, какие ид достпны для готовки

    val k = readLine()!!.toInt()
    for (i in 1..k){
        val params = readLine()!!.split(" ")
        ingredientNumber.putIfAbsent(params[1], ++primaryKeyId)
        for (j in 2 until params.size){
            ingredientNumber.putIfAbsent(params[j], ++primaryKeyId)
            matrixAdjacency[ingredientNumber[params[1]]!!].add(ingredientNumber[params[j]]!!)
        }
    }

    // Обновление ДП с помощью обхода в глубину
    for (i in 1..7){
        dfs(matrixAdjacency, i, dp)
    }

    // Пройтись по каждому инградиенту, найти его в dp и сложить..
    // Если есть невозможный инградиент, то он будет много
    var answer = 0L
    for (ingredient in ingredientList){
        answer += dp[ingredientNumber[ingredient]!!]
    }
    println(if (answer > CUSTOM_MAX_VALUE) -1 else answer)
}

// v - номер инградиента
// return мин стоимость
fun dfs(matrixAdjacency: List<List<Int>>, v: Int, dp: MutableList<Long>): Long {
    var sum = 0L
    for (i in matrixAdjacency[v].indices){
        sum += dfs(matrixAdjacency, matrixAdjacency[v][i], dp)
    }
    if (sum == 0L) sum = CUSTOM_MAX_VALUE
    dp[v] = min(dp[v], sum)
    return dp[v]
}
