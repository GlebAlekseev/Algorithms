package toff.backend

fun main(){
    val m = readln().toInt()
    val queue = Queue()
    for (i in 1..m){
        val request = readln()
        when(request[0]){
            '1' ->{
                val (_, arg) = request.split(" ")
                queue.push(arg)
            }
            '2' -> queue.expand()
            '3'-> queue.pop()
        }
    }
}

class Queue {
    private var head: Node<String>? = null
    private var tail: Node<String>? = null
    var size = 0
        private set

    fun push(value: String){
        if (tail?.value == value){
            tail!!.size++
        }else {
            defaultPush(value)
        }
    }

    fun pop(){
        println(head!!.value)
        if (head!!.size == 1){
            defaultPop()
        }else {
            head!!.size--
        }
    }

    private fun defaultPush(value: String) {
        if (size == 0) {
            head = Node(value,null,null)
            tail = head
        } else {
            tail!!.next = Node(value, null, tail)
            tail = tail!!.next
        }
        size++
    }

    private fun defaultPop(): String {
        return if (size == 1) {
            val headValue = head!!.value
            head = null
            tail = null
            size--
            headValue
        }else{
            val headValue = head!!.value
            head = head!!.next
            head!!.prev = null
            size--
            headValue
        }
    }

    fun expand(){
        var currentNode = head
        while (currentNode != null){
            currentNode.size *= 2
            currentNode = currentNode.next
        }
    }

    data class Node<T> internal constructor(
        var value: T,
        var next: Node<T>?,
        var prev: Node<T>?,
        var size: Int = 1
    )
}