package toff.search_trees

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextUInt

fun main() {
    val n = readLine()!!.toInt()
    val treap: TaskA = TreapTaskA()
    var lowerBound: Int? = null
    for (i in 1..n) {
        val request = readLine()!!.split(" ")
        val value = request[1].toInt()
        when (request[0]) {
            "+" -> {
                if (lowerBound == null) {
                    treap.add(value)
                } else {
                    val newValue = (value + lowerBound) % 10.0.pow(9).toInt()
                    treap.add(newValue)
                    lowerBound = null
                }
            }
            "?" -> println(treap.next(value).also { lowerBound = it })
        }
    }
}

class TreapTaskA : TaskA {
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

    override fun next(target: Int): Int {
        return Treap.lowerBound(treapRoot, target)
    }
}

interface TaskA {
    fun add(value: Int)
    fun next(target: Int): Int
}

class Treap(private val value: Int) {
    val priority: Int = Random(Instant.now().nano).nextUInt().toInt()
    var left: Treap? = null
    var right: Treap? = null

    companion object {
        fun split(treap: Treap?, target: Int): Pair<Treap?, Treap?> {
            treap ?: return Pair(null, null)
            if (target > treap.value) {
                val (left, right) = split(treap.right, target)
                treap.right = left
                return Pair(treap, right)
            } else {
                val (left, right) = split(treap.left, target)
                treap.left = right
                return Pair(left, treap)
            }
        }

        fun merge(leftTreap: Treap?, rightTreap: Treap?): Treap? {
            rightTreap ?: return leftTreap
            leftTreap ?: return rightTreap
            if (leftTreap.priority > rightTreap.priority) {
                leftTreap.right = merge(leftTreap.right, rightTreap)
                return leftTreap
            } else {
                rightTreap.left = merge(leftTreap, rightTreap.left)
            }
            return rightTreap
        }

        fun contains(root: Treap?, target: Int): Boolean {
            root ?: return false
            if (root.value == target) return true
            return if (root.value < target) contains(root.right, target)
            else contains(root.left, target)
        }

        fun lowerBound(root: Treap?, target: Int, min: Int = -1): Int {
            root ?: return -1
            if (root.value == target) return root.value
            return if (target > root.value) {
                root.right ?: return min
                lowerBound(root.right, target, min)
            } else {
                root.left ?: return root.value
                lowerBound(root.left, target, root.value)
            }
        }
    }
}


@RunWith(Parameterized::class)
class Test1(
    private val inputString: List<String>,
    private val expected: List<Int>,
) {
    @Test
    fun test() {
        val n = inputString.size
        val treap: TaskA = TreapTaskA()
        var lowerBound: Int? = null
        val actual = mutableListOf<Int>()
        for (i in 1..n) {
            val request = inputString[i - 1].split(" ")
            val value = request[1].toInt()
            when (request[0]) {
                "+" -> {
                    if (lowerBound == null) {
                        treap.add(value)
                    } else {
                        val newValue = (value + lowerBound) % 10.0.pow(9).toInt()
                        treap.add(newValue)
                        lowerBound = null
                    }
                }
                "?" -> actual.add(treap.next(value).also { lowerBound = it })
            }
        }
        assertEquals(expected, actual.toList())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(listOf("+ 1", "+ 3", "+ 3", "? 2", "+ 1", "? 4"), listOf(3, 4)),
                arrayOf(
                    listOf(
                        "+ 10",
                        "+ 3",
                        "+ 2",
                        "+ 12",
                        "+ 11",
                        "+ 19",
                        "+ 14",
                        "+ 7",
                        "+ 5",
                        "+ 9",

                        "? 2",
                        "? 4",
                        "? 15",
                        "? 22",
                        "? 8",

                        ), listOf(2, 5, 19, -1, 10)
                ),
                arrayOf(
                    listOf(
                        "+ 10",
                        "+ 3",
                        "+ 2",
                        "+ 12",
                        "+ 11",
                        "+ 19",
                        "+ 14",
                        "+ 7",
                        "+ 5",
                        "+ 9",

                        "? 2",
                        "? 4",
                        "? 15",
                        "? 22",
                        "? 8",

                        ), listOf(2, 5, 19, -1, 10)
                ),
                arrayOf(
                    listOf(
                        "+ 10",
                        "+ 3",
                        "+ 2",
                        "+ 12",
                        "+ 11",
                        "+ 19",
                        "+ 14",
                        "+ 7",
                        "+ 5",
                        "+ 9",

                        "? 2",
                        "? 4",
                        "? 15",
                        "? 22",
                        "? 8",

                        ), listOf(2, 5, 19, -1, 10)
                ),
                arrayOf(
                    listOf(
                        "+ 10",
                        "+ 3",
                        "+ 2",
                        "+ 12",
                        "+ 11",
                        "+ 19",
                        "+ 14",
                        "+ 7",
                        "+ 5",
                        "+ 9",

                        "? 2",
                        "? 4",
                        "? 15",
                        "? 22",
                        "? 8",

                        ), listOf(2, 5, 19, -1, 10)
                ),
                arrayOf(
                    listOf(
                        "+ 10",
                        "+ 3",
                        "+ 2",
                        "+ 12",
                        "+ 11",
                        "+ 19",
                        "+ 14",
                        "+ 7",
                        "+ 5",
                        "+ 9",

                        "? 2",
                        "? 4",
                        "? 15",
                        "? 22",
                        "? 8",

                        ), listOf(2, 5, 19, -1, 10)
                ),
            )
        }
    }
}

class Test2 {
    @Test
    fun test1() {
        val treapTaskA = TreapTaskA()
        treapTaskA.add(10)
        treapTaskA.add(8)
        treapTaskA.add(3)

        val a99 = treapTaskA.next(2)
        val a0 = treapTaskA.next(3)
        val a1 = treapTaskA.next(4)
        val a2 = treapTaskA.next(8)
        val a3 = treapTaskA.next(11)
    }
}