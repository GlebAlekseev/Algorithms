package toff.overkills

import kotlin.random.Random

fun main() {
    // O(3m)
    val (n, _) = readLine()!!.split(" ").map { it.toLong() }
    // O(3m)
    val rawAArray = readLine()!!
        .split(" ")
        .map { it.toLong() }
    // O(8m)
    val aArray = (rawAArray + rawAArray)
        .sorted()
        .reversed()
        .map { WrapperLong(it) }

    when (val result = recursive(n, aArray, mutableListOf())) {
        is Status.Count -> {
            // O(m)
            val minResult = result.list.minByOrNull { it.first }!!
            println("${minResult.first}\n${minResult.second}")
        }
        Status.Minus -> println(-1)
        Status.Zero -> println(0)
    }
}

fun recursive(n: Long, array: List<WrapperLong>, prefix: MutableList<WrapperLong>): Status {
    if (array.isEmpty()) return Status.Minus
    if (array.minOf { it.value } > n) return Status.Zero
    var status: Status = Status.Minus

    for ((index, item) in array.withIndex()) {
        if (item !in prefix) {
            prefix.add(item)
            val result = recursive(n, array.subList(index, array.size), prefix)
            if (status == Status.Minus && result == Status.Zero) status = result
            if (result is Status.Count) {
                status = if (status is Status.Count) {
                    Status.Count(status.list + result.list)
                } else result
            }
            prefix.removeAt(prefix.lastIndex)
        }
    }
    if (prefix.sumOf { it.value } == n) {
        if (status is Status.Count) {
            status =
                Status.Count(status.list + listOf(Pair(prefix.size, prefix.joinToString(" "))))
        } else {
            status = Status.Count(listOf(Pair(prefix.size, prefix.joinToString(" "))))
        }

    }
    if (prefix.sumOf { it.value } > n) status = Status.Zero
    return status
}

sealed class Status {
    object Zero : Status()
    object Minus : Status()
    data class Count(val list: List<Pair<Int, String>>) : Status()
}

data class WrapperLong(val value: Long) {
    private val hash = random.nextInt()

    override fun toString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return hash
    }

    override fun equals(other: Any?): Boolean {
        return this.hashCode() == (other as WrapperLong).hashCode()
    }

    companion object {
        val random = Random(System.currentTimeMillis())
    }
}