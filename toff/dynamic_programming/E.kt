package toff.dynamic_programming

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.min

fun main(){
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val matrix = Array(n){Array(m){0} }
    for (i in 0 until n){
        matrix[i] = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    }

    // ближайший ноль справа
    val matrixZeroRight = Array(n){Array(m){0} }
    for (i in 0 until n){
        for (j in m - 1 downTo 0){
            matrixZeroRight[i][j] = if (matrix[i][j] == 0) m - j - 1 else if(j == m - 1) -1 else matrixZeroRight[i][j + 1]
        }
    }

    // ближайший ноль снизу
    val matrixZeroBottom = Array(n){Array(m){0} }
    for (j in 0 until m){
        for (i in n - 1 downTo 0){
            matrixZeroBottom[i][j] = if (matrix[i][j] == 0) n - i - 1 else if(i == n - 1) -1 else matrixZeroBottom[i + 1][j]
        }
    }

    var lastMax = 1
    var lastI = 0
    var lastJ = 0

    val resultMatrix = Array(n){ Array(m) { 0 } }

    for (i in 0 until n){
        for (j in 0 until m){
            resultMatrix[i][j] = min(
                m - matrixZeroRight[i][j] - 1 - j,
                n - matrixZeroBottom[i][j] - 1 - i
            )
        }
    }

    for (i in 0 until n){
        for (j in 0 until m){
            if (resultMatrix[i][j] > lastMax) {
                // Проверить на квадрат..

                var l = resultMatrix[i][j]
                var counter = 0
                for (f in 1..resultMatrix[i][j]){
                    if (l == 0) break
                    counter++
                    if (l == 1) break
                    l = min(l - 1, resultMatrix[i + counter][j + counter])
                }
                if (counter > lastMax){
                    lastMax = counter
                    lastI = i
                    lastJ = j
                }
            }
        }
    }
    println(lastMax)
    println("${lastI + 1} ${lastJ + 1}")
}


@RunWith(Parameterized::class)
class Test1(
    private val nmInput: String,
    private val lines: List<String>,
    private val expectedLength: Int,
    private val expectedX: Int,
    private val expectedY: Int,
) {
    @Test
    fun test() {
        println("nmInput: $nmInput lines=$lines")
        val (n, m) = nmInput.split(" ").map { it.toInt() }
        val matrix = Array(n){Array(m){0} }
        for (i in 0 until n){
            matrix[i] = lines[i].split(" ").map { it.toInt() }.toTypedArray()
        }

        // ближайший ноль справа
        val matrixZeroRight = Array(n){Array(m){0} }
        for (i in 0 until n){
            for (j in m - 1 downTo 0){
                matrixZeroRight[i][j] = if (matrix[i][j] == 0) m - j - 1 else if(j == m - 1) -1 else matrixZeroRight[i][j + 1]
            }
        }

        // ближайший ноль снизу
        val matrixZeroBottom = Array(n){Array(m){0} }
        for (j in 0 until m){
            for (i in n - 1 downTo 0){
                matrixZeroBottom[i][j] = if (matrix[i][j] == 0) n - i - 1 else if(i == n - 1) -1 else matrixZeroBottom[i + 1][j]
            }
        }

        var lastMax = 1
        var lastI = 0
        var lastJ = 0

        val resultMatrix = Array(n){ Array(m) { 0 } }

        for (i in 0 until n){
            for (j in 0 until m){
                resultMatrix[i][j] = min(
                    m - matrixZeroRight[i][j] - 1 - j,
                    n - matrixZeroBottom[i][j] - 1 - i
                )
            }
        }

        for (i in 0 until n){
            for (j in 0 until m){
                if (resultMatrix[i][j] > lastMax) {
                    // Проверить на квадрат..

                    var l = resultMatrix[i][j]
                    var counter = 0
                    for (f in 1..resultMatrix[i][j]){
                        if (l == 0) break
                        counter++
                        if (l == 1) break
                        l = min(l - 1, resultMatrix[i + counter][j + counter])
                    }
                    if (counter > lastMax){
                        lastMax = counter
                        lastI = i
                        lastJ = j
                    }
                }
            }
        }
        println(lastMax)
        println("${lastI + 1} ${lastJ + 1}")

        Assert.assertEquals(lastMax, expectedLength)
        Assert.assertEquals(lastI + 1, expectedX)
        Assert.assertEquals(lastJ + 1, expectedY)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
//                arrayOf(
//                    "1 1",
//                    listOf("1"),
//                    1,
//                    1,
//                    1
//                ),
//                arrayOf(
//                    "3 5",
//                    listOf(
//                        "1 1 0 0 0",
//                        "1 1 1 1 1",
//                        "0 0 0 1 1"
//                    ),
//                    2,
//                    2,
//                    4
//                ),
//                arrayOf(
//                    "1 2",
//                    listOf(
//                        "1 1",
//                    ),
//                    1,
//                    1,
//                    2
//                ),
//                arrayOf(
//                    "1 2",
//                    listOf(
//                        "0 1",
//                    ),
//                    1,
//                    1,
//                    2
//                ),
//                arrayOf(
//                    "1 2",
//                    listOf(
//                        "1 0",
//                    ),
//                    1,
//                    1,
//                    1
//                ),
//                arrayOf(
//                    "6 8",
//                    listOf(
//                        "1 0 0 1 0 0 0 0",
//                        "1 0 1 0 0 1 0 0",
//                        "1 0 1 1 0 0 0 0",
//                        "1 0 1 1 1 0 0 1",
//                        "1 1 1 1 1 0 0 0",
//                        "0 0 1 0 1 0 0 0",
//                    ),
//                    2,
//                    4,
//                    4
//                ),
//                arrayOf(
//                    "4 4",
//                    listOf(
//                        "0 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 0",
//
//                    ),
//                    3,
//                    2,
//                    1
//                ),
//                arrayOf(
//                    "4 4",
//                    listOf(
//                        "1 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 0",
//
//                        ),
//                    3,
//                    2,
//                    1
//                ),
//                arrayOf(
//                    "4 4",
//                    listOf(
//                        "1 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 1",
//                        "1 1 1 1",
//
//                        ),
//                    3,
//                    2,
//                    1
//                ),
//                arrayOf(
//                    "2 2",
//                    listOf(
//                        "0 1",
//                        "1 1",
//
//                        ),
//                    3,
//                    2,
//                    1
//                ),
//                arrayOf(
//                    "5 5",
//                    listOf(
//                        "1 1 1 1 0",
//                        "1 1 1 0 1",
//                        "1 1 0 1 1",
//                        "1 0 1 1 1",
//                        "0 1 1 1 1",
//                        ),
//                    3,
//                    2,
//                    1
//                ),
                arrayOf(
                    "4 4",
                    listOf(
                        "1 1 1 1 1",
                        "1 1 1 1 1",
                        "1 1 1 1 1",
                        "1 1 1 1 1",
                        "1 1 1 0 1",
                    ),
                    3,
                    2,
                    1
                ),
            )
        }
    }
}


class Test3{
    @Test
    fun test1(){
        val nmInput = "5 5"
        val lines = listOf(
            "1 1 1 1 1",
            "1 1 1 0 0",
            "1 0 1 0 0",
            "1 0 0 0 0",
            "1 0 0 0 0",
        )

        val (n, m) = nmInput.split(" ").map { it.toInt() }
        val matrix = Array(n){Array(m){0} }
        for (i in 0 until n){
            matrix[i] = lines[i].split(" ").map { it.toInt() }.toTypedArray()
        }

        // ближайший ноль справа
        val matrixZeroRight = Array(n){Array(m){0} }
        for (i in 0 until n){
            for (j in m - 1 downTo 0){
                matrixZeroRight[i][j] = if (matrix[i][j] == 0) m - j - 1 else if(j == m - 1) -1 else matrixZeroRight[i][j + 1]
            }
        }

        // ближайший ноль снизу
        val matrixZeroBottom = Array(n){Array(m){0} }
        for (j in 0 until m){
            for (i in n - 1 downTo 0){
                matrixZeroBottom[i][j] = if (matrix[i][j] == 0) n - i - 1 else if(i == n - 1) -1 else matrixZeroBottom[i + 1][j]
            }
        }

        var lastMax = 1
        var lastI = 0
        var lastJ = 0

        val resultMatrix = Array(n){ Array(m) { 0 } }

        for (i in 0 until n){
            for (j in 0 until m){
                resultMatrix[i][j] = min(
                    m - matrixZeroRight[i][j] - 1 - j,
                    n - matrixZeroBottom[i][j] - 1 - i
                )
            }
        }

        for (i in 0 until n){
            for (j in 0 until m){
                if (resultMatrix[i][j] > lastMax) {
                    // Проверить на квадрат..

                    var l = resultMatrix[i][j]
                    var counter = 0
                    for (f in 1..resultMatrix[i][j]){
                        if (l == 0) break
                        counter++
                        if (l == 1) break
                        l = min(l - 1, resultMatrix[i + counter][j + counter])
                    }
                    if (counter > lastMax){
                        lastMax = counter
                        lastI = i
                        lastJ = j
                    }
                }
            }
        }

        resultMatrix.forEach {
            println(it.toList())
        }
        println("lastMax=$lastMax\n\ti=${lastI + 1} j=${lastJ + 1}")

    }
}
