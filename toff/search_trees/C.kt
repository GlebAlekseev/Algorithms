package toff.search_trees.C

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextUInt

fun main() {
    val n = readLine()!!.toInt()
    val treap: TaskC = TreapTaskC()
    for (i in 1..n) {
        val (type, arg) = readLine()!!.split(" ").map { it.toInt() }
        when (type) {
            1 -> treap.add(arg)
            0 -> println(treap.getNthMaximum(arg))
            -1 -> treap.remove(arg)
        }
    }
}

class TreapTaskC : TaskC {
    private var treapRoot: Treap? = null
    override fun add(value: Int) {
        val (left, right) = Treap.split(treapRoot, value)
        val middle = Treap(value)
        val leftMiddle = Treap.merge(left, middle)
        val leftMiddleRight = Treap.merge(leftMiddle, right)
        treapRoot = leftMiddleRight
    }

    override fun remove(value: Int) {
        val (left, middleRight) = Treap.split(treapRoot, value)
        val (middle, right) = Treap.split(middleRight, value + 1)
        treapRoot = Treap.merge(left, right)
    }

    override fun getNthMaximum(k: Int): Int {
        return Treap.getNthMaximum(treapRoot, k)
    }
}

interface TaskC {
    fun add(value: Int)
    fun remove(value: Int)
    fun getNthMaximum(k: Int): Int
}

class Treap(val value: Int) {
    var size: Int = 1
    val priority: Int = Random(Instant.now().nano).nextUInt().toInt()
    var left: Treap? = null
    var right: Treap? = null

    fun updateSize() {
        this.size = 1 + (left?.size ?: 0) + (right?.size ?: 0)
    }

    companion object {
        fun split(treap: Treap?, target: Int): Pair<Treap?, Treap?> {
            treap ?: return Pair(null, null)
            if (target > treap.value) {
                val (left, right) = split(treap.right, target)
                treap.right = left
                treap.updateSize()
                return Pair(treap, right)
            } else {
                val (left, right) = split(treap.left, target)
                treap.left = right
                treap.updateSize()
                return Pair(left, treap)
            }
        }

        fun merge(leftTreap: Treap?, rightTreap: Treap?): Treap? {
            rightTreap ?: return leftTreap
            leftTreap ?: return rightTreap
            if (leftTreap.priority > rightTreap.priority) {
                leftTreap.right = merge(leftTreap.right, rightTreap)
                leftTreap.updateSize()
                return leftTreap
            } else {
                rightTreap.left = merge(leftTreap, rightTreap.left)
                rightTreap.updateSize()
                return rightTreap
            }
        }

        fun getNthMaximum(treap: Treap?, k: Int): Int {
            val rootNumber = (treap!!.right?.size ?: 0) + 1
            if (rootNumber == k) return treap.value
            return if (k < rootNumber) {
                getNthMaximum(treap.right, k)
            } else {
                getNthMaximum(treap.left, k - rootNumber)
            }
        }
    }
}

class Test1 {
    @Test
    fun test1() {
        val treapTaskC = TreapTaskC()
        treapTaskC.add(5)
        treapTaskC.add(3)
        treapTaskC.add(7)
        treapTaskC.remove(5)
        treapTaskC.add(10)

        val res1 = treapTaskC.getNthMaximum(1)
        val res2 = treapTaskC.getNthMaximum(2)
        val res3 = treapTaskC.getNthMaximum(3)
        println(res1)
        println(res2)
        println(res3)
    }
}


@RunWith(Parameterized::class)
class Test2(
    private val inputString: List<String>,
    private val expected: List<Int>,
) {
    @Test
    fun test() {
        val treap: TaskC = TreapTaskC()
        val actual = mutableListOf<Int>()
        for (i in 1..inputString.size) {
            val (type, arg) = inputString[i - 1].split(" ").map { it.toInt() }
            when (type) {
                1 -> treap.add(arg)
                0 -> actual.add(treap.getNthMaximum(arg))
                -1 -> treap.remove(arg)
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
                        "+1 5",
                        "+1 3",
                        "+1 7",
                        "0 1",
                        "0 2",
                        "0 3",
                        "-1 5",
                        "+1 10",
                        "0 1",
                        "0 2",
                        "0 3",
                    ), listOf(7, 5, 3, 10, 7, 3)
                ),
            )
        }
    }
}