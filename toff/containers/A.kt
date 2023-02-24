package toff.containers

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun main() {
    val expression = readLine()!!.split(" ")
    val stack = Stack<String>()

    expression.forEach {
        when (it) {
            "+" -> {
                val l = stack.pop().toLong()
                val r = stack.pop().toLong()
                stack.push((l + r).toString())
            }
            "-" -> {
                val l = stack.pop().toLong()
                val r = stack.pop().toLong()
                stack.push((r - l).toString())
            }
            "*" -> {
                val l = stack.pop().toLong()
                val r = stack.pop().toLong()
                stack.push((l * r).toString())
            }
            else -> stack.push(it)
        }
    }
    println(stack.pop())
}

class Stack<T> {
    val array = mutableListOf<T>()
    fun push(value: T) {
        array.add(value)
    }
    fun pop(): T {
        return array.removeLast()
    }
}


@RunWith(Parameterized::class)
class Test1(
    private val inputString: String,
    private val expected: String,
) {
    @Test
    fun test() {
        println(inputString)
        val expression = inputString.split(" ")
        val stack = Stack<String>()
        expression.forEach {
            when (it) {
                "+" -> {
                    val l = stack.pop().toLong()
                    val r = stack.pop().toLong()
                    stack.push((l + r).toString())
                }
                "-" -> {
                    val l = stack.pop().toLong()
                    val r = stack.pop().toLong()
                    stack.push((r - l).toString())
                }
                "*" -> {
                    val l = stack.pop().toLong()
                    val r = stack.pop().toLong()
                    stack.push((l * r).toString())
                }
                "" -> {}
                else -> stack.push(it)
            }
        }
        val actual = stack.pop()
        println("expected=${expected} actual=${actual}")
        assertEquals(actual, expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("8 9 + 1 7 - *", "-102"),
                arrayOf("8 9 + 1 7 - *   ", "-102"),
                arrayOf("2 3 *", "6"),
                arrayOf("5 4 + 3 2 - *", "9"),
                arrayOf("1 2 + 3 * 4 -", "5"),
                arrayOf("5 6 +", "11"),
                arrayOf("10 5 -", "5"),
                arrayOf("3 4 + 5 *", "35"),
                arrayOf("7 2 3 * -", "1"),
                arrayOf("1 2 + 3 4 + *", "21"),
                arrayOf("2 3 4 * +", "14"),
                arrayOf("2 3 4 + *", "14"),
                arrayOf("2 3 4 - *", "-2"),
                arrayOf("5 6 7 + *", "65"),
                arrayOf("5 6 7 * +", "47"),
                arrayOf("10 5 2 * -", "0"),
                arrayOf("3 4 5 * +", "23"),
                arrayOf("2 3 4 * + 5 -", "9"),
                arrayOf("1 2 + 3 4 - *", "-3"),
                arrayOf("2 3 4 * -", "-10"),
                arrayOf("2 3 4 - +", "1"),
                arrayOf("9 9 + 9 + 9 + 9 + 9 + 9 + 9 + 9 + 9 +", "90"),
                arrayOf("1 2 + 3 4 + 5 6 + * *", "231"),
            )
        }
    }
}
