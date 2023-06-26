package ytrain.week_3

import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextUInt
// Что за жесть я написал =А / nlogn
fun main() {
    val treap = Treap()
    readln()
    val ages = readln().trim().split(" ").map { it.toInt() }.onEach {
        treap.add(it)
    }
    val countToReject = ages.fold(0L) { acc, age ->
        val l = (0.5 * age + 7).toInt()
        val r = age
        acc + if (r <= l) 0 else with(treap.amountOnSubsection(l + 1, r)) {
            if (age in l + 1..r) this - 1 else this
        }
    }
    println(countToReject)
}

data class Treap(
    private var treapRoot: TreapNode? = null
) {
    fun add(value: Int) {
        val (left, right) = TreapNode.split(treapRoot, value)
        val middle = TreapNode(value)
        val leftMiddle = TreapNode.merge(left, middle)
        val leftMiddleRight = TreapNode.merge(leftMiddle, right)
        treapRoot = leftMiddleRight
    }

    // l..r
    fun amountOnSubsection(l: Int, r: Int): Int {
        val (left, target1) = TreapNode.split(treapRoot, l)
        val (target2, right2) = TreapNode.split(target1, r + 1)
        val size = target2?.size ?: 0
        val merged = TreapNode.merge(target2, right2)
        treapRoot = TreapNode.merge(left, merged)
        return size
    }
}

class TreapNode(val value: Int) {
    var size: Int = 1
        private set
    private val priority: Int = Random(Instant.now().nano).nextUInt().toInt()
    private var left: TreapNode? = null
    private var right: TreapNode? = null

    private fun updateSize() {
        this.size = 1 + (left?.size ?: 0) + (right?.size ?: 0)
    }

    companion object {
        fun split(treapNode: TreapNode?, target: Int): Pair<TreapNode?, TreapNode?> {
            treapNode ?: return Pair(null, null)
            if (target > treapNode.value) {
                val (left, right) = split(treapNode.right, target)
                treapNode.right = left
                treapNode.updateSize()
                return Pair(treapNode, right)
            } else {
                val (left, right) = split(treapNode.left, target)
                treapNode.left = right
                treapNode.updateSize()
                return Pair(left, treapNode)
            }
        }

        fun merge(leftTreapNode: TreapNode?, rightTreapNode: TreapNode?): TreapNode? {
            rightTreapNode ?: return leftTreapNode
            leftTreapNode ?: return rightTreapNode
            if (leftTreapNode.priority > rightTreapNode.priority) {
                leftTreapNode.right = merge(leftTreapNode.right, rightTreapNode)
                leftTreapNode.updateSize()
                return leftTreapNode
            } else {
                rightTreapNode.left = merge(leftTreapNode, rightTreapNode.left)
                rightTreapNode.updateSize()
                return rightTreapNode
            }
        }
    }
}