package toff.fin.C

import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextUInt

fun main() {
    val n = readLine()!!.toInt()
    val treap = TreapC()
    for (i in 1..n) {
        val (type, param) = readLine()!!.split(" ")
        when (type) {
            "1" -> {
                treap.add(param)
            }
            "2" -> {
                println(treap.getNthMinimum(param.toInt()))
            }
        }
    }
}


class TreapC {
    private var treapRoot: Treap? = null
    fun add(value: String) {
        val (left, right) = Treap.split(treapRoot, value)
        val middle = Treap(value)
        val leftMiddle = Treap.merge(left, middle)
        val leftMiddleRight = Treap.merge(leftMiddle, right)
        treapRoot = leftMiddleRight
    }

    fun getNthMinimum(k: Int): String {
        return Treap.getNthMinimum(treapRoot, k)
    }
}

class Treap(val value: String) {
    var size: Int = 1
    val priority: Int = Random(Instant.now().nano).nextUInt().toInt()
    var left: Treap? = null
    var right: Treap? = null

    fun updateSize() {
        this.size = 1 + (left?.size ?: 0) + (right?.size ?: 0)
    }

    companion object {
        fun split(treap: Treap?, target: String): Pair<Treap?, Treap?> {
            treap ?: return Pair(null, null)
            if (target < treap.value) {
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

        fun getNthMinimum(treap: Treap?, k: Int): String {
            val rootNumber = (treap!!.right?.size ?: 0) + 1
            if (rootNumber == k) return treap.value
            return if (k < rootNumber) {
                getNthMinimum(treap.right, k)
            } else {
                getNthMinimum(treap.left, k - rootNumber)
            }
        }
    }
}