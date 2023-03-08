package toff.search_trees.D

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextUInt

fun main() {
    val n = readLine()!!.toInt()
    val treap: TaskD = TreapTaskD()
    var sum: Long? = null
    for (i in 1..n) {
        val request = readLine()!!.split(" ")
        when (request[0]) {
            "+" -> {
                if (sum == null) {
                    treap.add(request[1].toInt())
                } else {
                    val newValue = ((request[1].toInt() + sum) % 10.0.pow(9).toInt()).toInt()
                    treap.add(newValue)
                    sum = null
                }
            }
            "?" -> println(treap.sum(request[1].toInt(), request[2].toInt()).also { sum = it })
        }
    }
}

class TreapTaskD : TaskD {
    private var treapRoot: Treap? = null

    override fun add(value: Int) {
        val exists = Treap.contains(treapRoot, value)
        if (!exists) {
            val (left, right) = Treap.split(treapRoot, value)
            val middle = Treap(value)
            val leftMiddle = Treap.merge(left, middle)
            val leftMiddleRight = Treap.merge(leftMiddle, right)
            treapRoot = leftMiddleRight
        }
    }

    override fun sum(left: Int, right: Int): Long {
        val (leftRange, middleRightRange) = Treap.split(treapRoot, left)
        val (middleRange, rightRange) = Treap.split(middleRightRange, right + 1)
        val sum = middleRange?.sum ?: 0L
        treapRoot = Treap.merge(leftRange, Treap.merge(middleRange, rightRange))
        return sum
    }
}

interface TaskD {
    fun add(value: Int)
    fun sum(left: Int, right: Int): Long
}

class Treap(private val value: Int) {
    val priority: Int = Random(Instant.now().nano).nextUInt().toInt()
    var left: Treap? = null
    var right: Treap? = null
    var sum: Long = value.toLong()

    fun updateSum() {
        this.sum = value.toLong() + (left?.sum ?: 0L) + (right?.sum ?: 0L)
    }

    companion object {
        fun split(treap: Treap?, target: Int): Pair<Treap?, Treap?> {
            treap ?: return Pair(null, null)
            if (target > treap.value) {
                val (left, right) = split(treap.right, target)
                treap.right = left
                left?.updateSum()
                treap.updateSum()
                return Pair(treap, right)
            } else {
                val (left, right) = split(treap.left, target)
                treap.left = right
                right?.updateSum()
                treap.updateSum()
                return Pair(left, treap)
            }
        }

        fun merge(leftTreap: Treap?, rightTreap: Treap?): Treap? {
            rightTreap ?: return leftTreap
            leftTreap ?: return rightTreap
            if (leftTreap.priority > rightTreap.priority) {
                leftTreap.right = merge(leftTreap.right, rightTreap)
                leftTreap.right?.updateSum()
                leftTreap.updateSum()
                return leftTreap
            } else {
                rightTreap.left = merge(leftTreap, rightTreap.left)
                leftTreap.left?.updateSum()
                rightTreap.updateSum()
                return rightTreap
            }
        }

        fun contains(root: Treap?, target: Int): Boolean {
            root ?: return false
            if (root.value == target) return true
            return if (root.value < target) contains(root.right, target)
            else contains(root.left, target)
        }
    }
}

@RunWith(Parameterized::class)
class Test2(
    private val inputString: List<String>,
    private val expected: List<Long>,
) {
    @Test
    fun test() {
        val treap: TaskD = TreapTaskD()
        var sum: Long? = null
        val actual = mutableListOf<Long>()
        for (i in 1..inputString.size) {
            val request = inputString[i - 1].split(" ")
            when (request[0]) {
                "+" -> {
                    if (sum == null) {
                        treap.add(request[1].toInt())
                    } else {
                        val newValue = ((request[1].toInt() + sum) % 10.0.pow(9).toInt()).toInt()
                        treap.add(newValue)
                        sum = null
                    }
                }
                "?" -> actual.add(treap.sum(request[1].toInt(), request[2].toInt()).also { sum = it })
            }
        }
        Assert.assertEquals(actual.toList(), expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(
                        "+ 1",
                        "+ 3",
                        "+ 3",
                        "? 2 4",
                        "+ 1",
                        "? 2 4",
                    ), listOf(3L, 7L)
                ),
            )
        }
    }
}