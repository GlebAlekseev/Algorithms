package toff.containers.E

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun main() {
    readLine()
    val arrayN = readLine()!!.split(" ","\n").map { it.toInt() }.toIntArray()
    val leftConstrains = findNearestSmallerElements(arrayN)
    val rightConstrains =
        findNearestSmallerElements(arrayN.reversed().toIntArray()).reversed().map { arrayN.size - it - 1 }
    val prefixSums = prefixSum(arrayN)

    var maxSums = 0L
    for (i in arrayN.indices) {
        val sum =
            (prefixSums[rightConstrains[i] - 1] - if (leftConstrains[i] == -1) 0L else prefixSums[leftConstrains[i]]) * arrayN[i]
        if (sum > maxSums) maxSums = sum
    }
    println(maxSums)
}

fun prefixSum(array: IntArray): LongArray {
    val prefixSums = LongArray(array.size)
    var sum = 0L
    for (i in array.indices) {
        sum += array[i]
        prefixSums[i] = sum
    }
    return prefixSums
}

fun findNearestSmallerElements(arr: IntArray): Array<Int> {
    val result = Array(arr.size) { -1 }
    val stack = mutableListOf<Int>()
    for (i in arr.indices) {
        while (stack.isNotEmpty() && arr[stack.last()] >= arr[i]) {
            stack.removeLast()
        }
        if (stack.isNotEmpty()) {
            result[i] = stack.last()
        }
        stack.add(i)
    }
    return result
}

@RunWith(Parameterized::class)
class Test1(
    private val inputString: String,
    private val expected: String,
) {
    @Test
    fun test() {
        println("inputString: $inputString")

        val arrayN = inputString.split(" ", "\n").map { it.toInt() }.toIntArray()
        val leftConstrains = findNearestSmallerElements(arrayN)
        val rightConstrains =
            findNearestSmallerElements(arrayN.reversed().toIntArray()).reversed().map { arrayN.size - it - 1 }
        val prefixSums = prefixSum(arrayN)

        println("arrayN=${arrayN.toList()}")
        println("leftConstrains=${leftConstrains.toList()}")
        println("rightConstrains=${rightConstrains.toList()}")
        println("prefixSums=${prefixSums.toList()}")
        Long.MAX_VALUE
        var maxSums = 0L
        for (i in arrayN.indices) {
            val sum =
                (prefixSums[rightConstrains[i] - 1] - if (leftConstrains[i] == -1) 0L else prefixSums[leftConstrains[i]]) * arrayN[i]
            if (sum > maxSums) maxSums = sum
        }

        val actual = maxSums
        println("expected=${expected} actual=${actual}")
        Assert.assertEquals(actual, expected.toLong())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("3 1 6 4 5 2", "60"),
                arrayOf("1 2 1 2", "6"),
                arrayOf("2 1 2", "5"),
                arrayOf("2 2 1", "8"),
                arrayOf("3\n1\n6\n4\n5\n2", "60"),
                arrayOf("1 2 1\n2", "6"),
                arrayOf("2\n1 2", "5"),
                arrayOf("2\n2\n1", "8"),
                arrayOf("1", "1"),
                arrayOf("0", "0"),
                )
        }
    }
}

