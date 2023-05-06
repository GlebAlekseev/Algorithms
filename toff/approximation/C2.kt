import kotlin.math.exp
import kotlin.random.Random

fun main()  {
    val (n, m, c) = readLine()!!.split(" ").map { it.toInt() }
    val rand = Random(46)
    val result = List(n) { MutableList(m) { rand.nextInt(1, c + 1) } }
    var t = 1.0
    var currentMin = f3(result)
    var i = 0
    var coolingRate = 0.9999
    var previousMin = currentMin
    while (i < Int.MAX_VALUE && currentMin > 0) {
        t *= coolingRate
        val r1 = rand.nextInt(0, n)
        val r2 = rand.nextInt(0, m)
        val r3 = rand.nextInt(1, c + 1)
        val old1 = result[r1][r2]
        result[r1][r2] = r3

        val value = f3(result)
        if (value <= currentMin || exp((currentMin - value) / t) > rand.nextInt(0,2)) {
            currentMin = value
        }else{
            result[r1][r2] = old1
        }
        i++
        if (i % 10 == 0) {
            if (currentMin < previousMin) {
                coolingRate += 0.001
            } else {
                coolingRate -= 0.001
            }
            coolingRate = coolingRate.coerceIn(0.0000001, 1.0)
            previousMin = currentMin
        }
    }
    result.forEach { println(it.joinToString(" ")) }
}

fun f3(rearrangement: List<List<Int>>): Int {
    var counter = 0
    val n = rearrangement.size
    val m = rearrangement[0].size
    for (i in 0 until n - 1) {
        for (j in 0 until m - 1) {
            for (k in i + 1 until n) {
                if (rearrangement[i][j] != rearrangement[k][j]) continue
                for (v in j + 1 until m) {
                    if (
                        rearrangement[i][j] == rearrangement[k][v] &&
                        rearrangement[k][v] == rearrangement[i][v]
                    ) {
                        counter++
                    }
                }
            }
        }
    }
    return counter
}
