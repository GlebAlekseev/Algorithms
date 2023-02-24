package toff.containers

fun main() {
    val exampleList = listOf(1, 2, 3, 4, 6, 8)

    val skipList = SkipList()
    skipList.insert(5)
    skipList.insert(3)
    skipList.insert(4)
    skipList.insert(1)

    skipList.findNode(4)

}

class SkipList {
    private var head: Node<Int>? = Node(Int.MIN_VALUE, null, null, null, null)

    // upperbound or value.
    fun findNode(target: Int): Node<Int>? {
        var node = head
        while (node?.nextLevel != null) {
            while (node!!.next != null && node.next!!.value <= target) {
                node = node.next
            }
            node = node.nextLevel
        }
        return node
    }

    fun insert(value: Int) {
        val node = findNode(value)
        val newNode = Node(value, null, null, null, null)
        // ...
    }

    data class Node<T> internal constructor(
        var value: T,
        var next: Node<T>?,
        var prev: Node<T>?,
        var nextLevel: Node<T>?,
        var prevLevel: Node<T>?
    )
}