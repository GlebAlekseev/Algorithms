package cormen.c10_базовые_структуры.theory

import cormen.c10_базовые_структуры.theory.DLinkedList.Companion.Node
import java.util.function.Consumer

fun main(){

}


class DLinkedList: Iterable<Int>{
    private var head: Node? = null
    private var tail: Node? = null
    var size = 0



    operator fun get(index: Int): Int{
        if (index == size-1) return tail?.key!!
        val node = findOfIndex(index)
        return node?.key!!
    }

    // O(1)
    fun pushBack(key: Int){
        if (size == 0){
            tail = Node(key = key)
            head = tail
        }else{
            tail?.next = Node(key = key, prev = tail)
            tail = tail?.next
        }
        size++
    }
    // O(1)
    fun pushFront(key: Int){
        if (size == 0){
            tail = Node(key = key)
            head = tail
        }else{
            head?.prev = Node(key = key, next = head)
            head = head?.prev
        }
        size++
    }
    // O(1)
    fun removeLast(){
        if (size != 0){
            if (size == 1){
                tail = null
                head = null
            }else{
                tail?.prev?.next = null
                tail = tail?.prev
            }
            size--
        }else{
            throw RuntimeException("Список уже пуст")
        }
    }
    // O(1)
    fun removeFirst(){
        if (size != 0){
            if (size == 1){
                tail = null
                head = null
            }else{
                head?.next?.prev = null
                head = head?.next
            }
            size--
        }else{
            throw RuntimeException("Список уже пуст")
        }
    }

    // O(n)
    private fun findOfIndex(i: Int): Node?{
        var findedNode: Node? = null
        head?.forEachIndexed{ index, node ->
            if (i == index) findedNode = node
        }
        return findedNode
    }
    // O(n)
    private fun findOfValue(value: Int): Pair<Node?, Int> {
        var pair: Pair<Node?, Int> = Pair(null,-1)
        head?.forEachIndexed { index, node ->
            if (node.key == value) pair = Pair(node, index)
        }
        return pair
    }

    fun edit(key: Int,index: Int){
        val node = findOfIndex(index)
        node?.key = key
    }

    fun insert(key: Int,index: Int){
        val node = findOfIndex(index)
        val nextNode = node?.next
        val prevNode = node?.prev
        val newNode = node?.copy(key = key)
        nextNode?.prev = newNode
        prevNode?.next = newNode
        size++
    }

    fun removeOfValue(key: Int){
        val (node, index) = findOfValue(key)
        if (index == 0) removeFirst()
        else if (index == size) removeLast()
        else{
            val nextNode = node?.next
            val prevNode = node?.prev
            nextNode?.prev = prevNode
            prevNode?.next = nextNode
        }
        size--
    }

    fun removeOfIndex(index: Int){
        val node = findOfIndex(index)
        if (index == 0) removeFirst()
        else if (index == size-1) removeLast()
        else{
            val nextNode = node?.next
            val prevNode = node?.prev
            nextNode?.prev = prevNode
            prevNode?.next = nextNode
        }
        size--
    }

    companion object{
        data class Node(var key: Int,var prev: Node? = null, var next: Node? = null): Iterable<Node>{
            override fun iterator(): Iterator<Node> = object : Iterator<Node>{
                var lastNode = this@Node
                override fun hasNext(): Boolean {
                    return lastNode.next == null
                }

                override fun next(): Node {
                    val tmpNode = lastNode
                    lastNode = lastNode.next!!
                    return tmpNode
                }
            }
        }
    }

    override fun iterator(): Iterator<Int> = object : Iterator<Int>{
        var lastNode = head
        override fun hasNext(): Boolean {
            return lastNode?.next == null
        }

        override fun next(): Int {
            val tmpNode = lastNode
            lastNode = lastNode?.next
            return tmpNode?.key!!
        }

    }
}

