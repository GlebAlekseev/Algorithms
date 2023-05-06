package toff.approximation.C

import kotlin.math.exp
import kotlin.random.Random

fun main(){
    for (i in 1..100){
        val res = fmain(i)
        println("seed=$i res=$res")
    }
}

fun fmain(seed: Int): Int {
//    val (n, m, c) = readLine()!!.split(" ").map { it.toInt() }
    val (n, m, c) = Triple(10,10,3)
    val rand = Random(seed)
    val result = List(n) { MutableList(m) { rand.nextInt(1, c + 1) } }
    var t = 1.0
    var currentMin = f3(result)
    var i = 0
    var coolingRate = 0.9999
    var previousMin = currentMin
    while (i < 100000 && currentMin > 0) {
//    while (i < Int.MAX_VALUE && currentMin > 0) {
        t *= coolingRate
        val r1 = rand.nextInt(0, n)
        val r2 = rand.nextInt(0, m)
        val r3 = rand.nextInt(1, c + 1)
        val old1 = result[r1][r2]
        result[r1][r2] = r3

        val value = f3(result)
        if (value <= currentMin || exp((currentMin - value) / t) > rand.nextInt(0,2)) {
//            println("i=$i t=$t coolingRate=$coolingRate value=$value")
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
//    result.forEach { println(it.joinToString(" ")) }
    return f3(result)
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

//fun f2(rearrangement: List<List<Int>>): Int {
//    var counter = 0
//    val n = rearrangement.size
//    val m = rearrangement[0].size
//
//    for (i in 0 until n - 1) {
//        for (j in 0 until m - 1) {
//            if (
//                rearrangement[i][j] == rearrangement[i][j + 1] &&
//                rearrangement[i][j] == rearrangement[i + 1][j] &&
//                rearrangement[i][j] == rearrangement[i + 1][j + 1]
//            ) counter++
//        }
//    }
//    return counter
//}


// Кол-во закрашенных прямоугольников, одна из сторон которого больше 1
// 0 - target
//fun f(rearrangement: List<List<Int>>): Int {
//    var counter = 0
//    val n = rearrangement.size
//    val m = rearrangement[0].size
//    for (i in 0 until n) { // i строка
//        for (j in 0 until m) { // j столбец
////            println("i=$i j=$j")
//            var maxM = m
//            for (k in i until n) { // k строка
//                for (v in j until maxM) { // v столбец
//                    if (i == k && j == v) continue
////                    println("\ti=$i j=$j _ k=$k v=$v status=${rearrangement[i][j] == rearrangement[k][v]}")
//                    if (rearrangement[i][j] == rearrangement[k][v]) {
//                        if (k - i > 0 && v - j > 0) {
//                            counter++
////                            println("\t\tcounter++")
//                        }
//                    } else {
//                        maxM = v
//                        break
//                    }
//                }
//            }
//        }
//    }
//    return counter
//}