package toff.approximation.B

import java.io.File
import kotlin.math.exp
import kotlin.random.Random

fun main(){
    for (i in 7..8){
        val res = fmain(i)
        println("seed=$i res=$res")
    }
}

fun fmain(seed: Int): Long {
    val rand = Random(seed)
    val list = mutableListOf<Int>()
    // Прочитать файл и заполнить матрицу расстояний
    File("./toff/approximation/B.txt").forEachLine {
        it.trim().split(Regex(" +")).forEach {
            list.add(it.toInt())
        }
    }
    val distanceMatrixRaw = list.windowed(312,312)


    val distanceMatrix = distanceMatrixRaw.map { it.toMutableList() }.toMutableList()
    distanceMatrix.add(0, MutableList(312){0})
    distanceMatrix.forEach {
        it.add(0, 0)
    }

    val result = (1..312).toMutableList()
    var t = 10.0
    var currentMin = f(distanceMatrix, result)
    var i = 0
    var coolingRate = 0.99
    var previousMin = currentMin
    while (i < 1000000) {
        t *= coolingRate
        val r1 = rand.nextInt(0, result.size)
        val r2 = rand.nextInt(0, result.size)

        result[r1] = result[r2].also { result[r2] = result[r1] }

        val value = f(distanceMatrix, result)

        if (value < currentMin || exp((currentMin - value) / t) > rand.nextInt(0,2)) {
            currentMin = value
        }else{
            result[r1] = result[r2].also { result[r2] = result[r1] }
        }
        i++
        if (i % 10 == 0) {
            if (currentMin < previousMin) {
                coolingRate += 0.001
            } else {
                coolingRate -= 0.001
            }
            coolingRate = coolingRate.coerceIn(0.001, 1.0)
            previousMin = currentMin
        }
    }

//    println(result.map{ it }.joinToString(" "))
//    println(f(distanceMatrix, result))
    return f(distanceMatrix, result)
}


// принимает матрицу и текущий стейт.. на освнове траектории вычисляет путь
fun f(distanceMatrix: List<List<Int>>, way: List<Int>): Long {
    var sum = 0L
    for (i in 0 until way.size - 1){
        val distance = distanceMatrix[way[i]][way[i+1]]
        sum += distance
    }
    return sum
}