package toff.containers.C

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun main() {
    readLine()
    val arrayN = readLine()!!.split(" ").map { it.toInt() }
    val stack = Stack<Int>()
    val result = mutableListOf<Pair<Int, Int>>()
    var desiredItem = 1

    var counter1 = 0
    var counter2 = 0
    arrayN.forEachIndexed { _, item ->
        stack.push(item)
        counter1++
        if (counter2 != 0) result.add(Pair(2, counter2)).also { counter2 = 0 }
        while (stack.top() == desiredItem) {
            stack.pop()
            desiredItem++
            counter2++
            if (counter1 != 0) result.add(Pair(1, counter1)).also { counter1 = 0 }
            counter1 = 0
        }
    }
    if (counter2 != 0) result.add(Pair(2, counter2)).also { counter2 = 0 }
    if (counter1 != 0) result.add(Pair(1, counter1)).also { counter1 = 0 }

    if (!stack.isEmpty()) {
        println(0)
    } else {
        println(result.size)
        result.forEach {
            println("${it.first} ${it.second}")
        }
    }
}

class Stack<T> {
    fun isEmpty() = array.isEmpty()
    val array = mutableListOf<T>()
    fun push(value: T) {
        array.add(value)
    }

    fun top(): T? = array.lastOrNull()

    fun pop(): T? {
        return array.removeLastOrNull()
    }
}


@RunWith(Parameterized::class)
class Test1(
    private val inputString: String,
    private val expected: String,
) {
    @Test
    fun test() {
        println("inputString: $inputString")

        val arrayN = inputString.split(" ").map { it.toInt() }
        val stack = Stack<Int>()
        val result = mutableListOf<Pair<Int, Int>>()
        var desiredItem = 1

        var counter1 = 0
        var counter2 = 0
        arrayN.forEachIndexed { _, item ->
            stack.push(item)
            counter1++
            if (counter2 != 0) result.add(Pair(2, counter2)).also { counter2 = 0 }
            while (stack.top() == desiredItem) {
                stack.pop()
                desiredItem++
                counter2++
                if (counter1 != 0) result.add(Pair(1, counter1)).also { counter1 = 0 }
                counter1 = 0
            }
        }
        if (counter2 != 0) result.add(Pair(2, counter2)).also { counter2 = 0 }
        if (counter1 != 0) result.add(Pair(1, counter1)).also { counter1 = 0 }

        val actual = if (!stack.isEmpty()) "0" else result.toString()
        println("expected=${expected} actual=${actual}")
        Assert.assertEquals(actual, expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    "3 2 1", listOf(
                        Pair(1, 3),
                        Pair(2, 3),
                    ).toString()
                ),
                arrayOf(
                    "4 1 3 2", listOf(
                        Pair(1, 2),
                        Pair(2, 1),
                        Pair(1, 2),
                        Pair(2, 3),
                    ).toString()
                ),
                arrayOf(
                    "1", listOf(
                        Pair(1, 1),
                        Pair(2, 1),
                    ).toString()
                ),
                arrayOf(
                    "7 1 6 3 4 5 2", "0"
                ),
            )
        }
    }
}

