package toff.containers.B

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun main() {
    val (N, K) = readLine()!!.split(" ").map { it.toInt() }
    val arrayN = readLine()!!.split(" ").map { it.toInt() }

    val queue = Queue()
    for (j in 0 until K - 1) {
        queue.push(arrayN[j])
    }

    for (i in 0..N - K) {
        var j = i + K - 1
        queue.push(arrayN[j])
        if (i == 0) print(queue.minimum()) else print(" ${queue.minimum()}")
        queue.pop(arrayN[i])
    }
}

// https://e-maxx.ru/algo/stacks_for_minima

class Queue {
    private val array = mutableListOf<Int>()
    private var i = 0
    private var j = 0
    private fun isEmpty() = j - i == 0
    private fun front() = if (!isEmpty()) array[i] else throw RuntimeException()
    private fun back() = if (!isEmpty()) array[j - 1] else throw RuntimeException()
    fun minimum() = front()
    fun push(value: Int) {
        while (!isEmpty() && back() > value) {
            j--
        }
        if (j < array.size) array[j] = value.also { j++ } else array.add(value).also { j++ }
    }

    fun pop(value: Int) {
        if (!isEmpty() && front() == value) {
            i++
        }
    }
}


@RunWith(Parameterized::class)
class Test1(
    private val inputString1: String,
    private val inputString2: String,
    private val expected: String,
) {
    @Test
    fun test() {
        println("inputString1: $inputString1\ninputString2: $inputString2")

        val (N, K) = inputString1.split(" ").map { it.toInt() }
        val arrayN = inputString2.split(" ").map { it.toInt() }

        val queue = Queue()
        for (j in 0 until K - 1) {
            queue.push(arrayN[j])
        }

        val stringBuilder = StringBuilder()

        for (i in 0..N - K) {
            var j = i + K - 1
            queue.push(arrayN[j])
            if (i == 0) stringBuilder.append(queue.minimum()) else stringBuilder.append(" ${queue.minimum()}")
            queue.pop(arrayN[i])
        }

        val actual = stringBuilder.toString()
        println("expected=${expected} actual=${actual}")
        Assert.assertEquals(actual, expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("7 3", "1 3 2 4 5 3 1", "1 2 2 3 1"),

                arrayOf("7 7", "1 2 3 4 5 6 7", "1"),
                arrayOf("7 6", "1 2 3 4 5 6 7", "1 2"),
                arrayOf("7 5", "1 2 3 4 5 6 7", "1 2 3"),
                arrayOf("7 4", "1 2 3 4 5 6 7", "1 2 3 4"),
                arrayOf("7 3", "1 2 3 4 5 6 7", "1 2 3 4 5"),
                arrayOf("7 2", "1 2 3 4 5 6 7", "1 2 3 4 5 6"),
                arrayOf("7 1", "1 2 3 4 5 6 7", "1 2 3 4 5 6 7"),

                arrayOf("7 1", "7 6 5 4 3 2 1", "7 6 5 4 3 2 1"),
                arrayOf("7 2", "7 6 5 4 3 2 1", "6 5 4 3 2 1"),
                arrayOf("7 3", "7 6 5 4 3 2 1", "5 4 3 2 1"),
                arrayOf("7 4", "7 6 5 4 3 2 1", "4 3 2 1"),
                arrayOf("7 5", "7 6 5 4 3 2 1", "3 2 1"),
                arrayOf("7 6", "7 6 5 4 3 2 1", "2 1"),
                arrayOf("7 7", "7 6 5 4 3 2 1", "1"),

                arrayOf("6 6", "7 4 5 2 3 1", "1"),
                arrayOf("6 5", "7 4 5 2 3 1", "2 1"),
                arrayOf("6 4", "7 4 5 2 3 1", "2 2 1"),
                arrayOf("6 3", "7 4 5 2 3 1", "4 2 2 1"),
                arrayOf("6 2", "7 4 5 2 3 1", "4 4 2 2 1"),
                arrayOf("6 1", "7 4 5 2 3 1", "7 4 5 2 3 1"),
                )
        }
    }
}
