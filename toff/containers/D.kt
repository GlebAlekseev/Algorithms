package toff.containers.D

fun main() {
    val N = readLine()!!.toInt()
    val queue = Queue()
    for (i in 0 until N) {
        val request = readLine()!!.split(" ")
        when (request[0]) {
            "+" -> queue.push(request[1])
            "*" -> queue.pushMiddle(request[1])
            "-" -> println(queue.pop())
        }
    }
}

class Queue {
    private var head: Node<String>? = null
    private var tail: Node<String>? = null
    private var middleNode: Node<String>? = null // Node, в next которой вставлять, для центра
    var size = 0
        private set

    fun push(value: String) {
        if (size == 0) {
            head = Node(value,null,null)
            tail = head
            middleNode = head
        } else {
            tail!!.next = Node(value, null, tail)
            tail = tail!!.next
        }
        size++

        if (size != 1 && size % 2 == 1) middleNode = middleNode!!.next
    }

    fun pushMiddle(value: String) {
        if (middleNode == null) push(value).also { return }
        if (middleNode!!.next == null){
            middleNode!!.next = Node(value, null, middleNode)
            tail = middleNode!!.next
        }else{
            val newNode = Node(value, middleNode!!.next, middleNode)
            middleNode!!.next!!.prev = newNode
            middleNode!!.next = newNode
            if (size % 2 == 0) middleNode = newNode
        }
        size++
    }

    fun pop(): String {
        if (size == 0) throw RuntimeException()
        return if (size == 1) {
            val headValue = head!!.value
            head = null
            middleNode = null
            tail = null
            size--
            headValue
        }else{
            val headValue = head!!.value
            head = head!!.next
            head!!.prev = null
            size--
            if (size % 2 == 1) middleNode = middleNode!!.next
            headValue
        }
    }

    data class Node<T> internal constructor(
        var value: T,
        var next: Node<T>?,
        var prev: Node<T>?
    )
}