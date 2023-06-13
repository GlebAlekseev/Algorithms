package ytrain

import java.util.*

fun main() {
    val path = readLine()!!
    val stack = Stack<String>()
    path.split("/").forEach { name ->
        when (name) {
            "", "." -> {}
            ".." -> {
                if (stack.isNotEmpty()) stack.pop()
            }
            else -> {
                stack.add(name)
            }
        }
    }
    val result = "/" + stack.joinToString("/")
    println(result)
}