package cormen.c10_базовые_структуры.theory

import cormen.c10_базовые_структуры.theory.DLinkedList.Companion.Node
import java.util.function.Consumer

fun main(){
    val list = DLinkedList<Int>()
    list.pushBack(1)
    list.pushBack(2)
    list.pushBack(3)
    list.pushBack(4)
    list.pushBack(5)
    list.pushFront(-1)
    list.pushFront(-2)
    list.pushFront(-3)
    list.forEach{
        println("it=$it")
    }

    list.removeFirst()
    list.removeFirst()

    list.removeLast()
    list.removeOfIndex(2)
    list.removeOfIndex(2)

    println()
    list.forEach{
        println("it=$it")
    }
}


class DLinkedList<T>: Iterable<T>{
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var _size = 0
    val size
        get() = _size


    operator fun get(index: Int): T{
        if (index == _size-1) return tail?.key!!
        val node = findOfIndex(index)
        return node?.key!!
    }

    // O(1)
    fun pushBack(key: T){
        if (_size == 0){
            tail = Node(key = key)
            head = tail
        }else{
            tail?.next = Node(key = key, prev = tail)
            tail = tail?.next
        }
        _size++
    }
    // O(1)
    fun pushFront(key: T){
        if (_size == 0){
            tail = Node(key = key)
            head = tail
        }else{
            head?.prev = Node(key = key, next = head)
            head = head?.prev
        }
        _size++
    }
    // O(1)
    fun removeLast(){
        if (_size != 0){
            if (_size == 1){
                tail = null
                head = null
            }else{
                tail?.prev?.next = null
                tail = tail?.prev
            }
            _size--
        }else{
            throw RuntimeException("Список уже пуст")
        }
    }
    // O(1)
    fun removeFirst(){
        if (_size != 0){
            if (_size == 1){
                tail = null
                head = null
            }else{
                head?.next?.prev = null
                head = head?.next
            }
            _size--
        }else{
            throw RuntimeException("Список уже пуст")
        }
    }

    // O(n)
    private fun findOfIndex(i: Int): Node<T>?{
        var findedNode: Node<T>? = null
        head?.forEachIndexed{ index, node ->
            if (i == index) findedNode = node
        }
        return findedNode
    }
    // O(n)
    private fun findOfValue(value: T): Pair<Node<T>?, Int> {
        var pair: Pair<Node<T>?, Int> = Pair(null,-1)
        head?.forEachIndexed { index, node ->
            if (node.key == value) pair = Pair(node, index)
        }
        return pair
    }


    fun edit(key: T,index: Int){
        val node = findOfIndex(index)
        node?.key = key
    }

    fun insert(key: T,index: Int){
        val node = findOfIndex(index)
        val nextNode = node?.next
        val prevNode = node?.prev
        val newNode = node?.copy(key = key)
        nextNode?.prev = newNode
        prevNode?.next = newNode
        _size++
    }


    fun removeOfValue(key: T){
        val (node, index) = findOfValue(key)
        if (index == 0) removeFirst()
        else if (index == _size) removeLast()
        else{
            val nextNode = node?.next
            val prevNode = node?.prev
            nextNode?.prev = prevNode
            prevNode?.next = nextNode
            _size--
        }
    }

    fun removeOfIndex(index: Int){
        val node = findOfIndex(index)
        if (index == 0) removeFirst()
        else if (index == _size-1) removeLast()
        else{
            val nextNode = node?.next
            val prevNode = node?.prev
            nextNode?.prev = prevNode
            prevNode?.next = nextNode
            _size--
        }
    }

    companion object{
        data class Node<T>(var key: T,var prev: Node<T>? = null, var next: Node<T>? = null): Iterable<Node<T>>{
            override fun iterator(): Iterator<Node<T>> = object : Iterator<Node<T>>{
                var lastNode: Node<T>? = this@Node
                override fun hasNext(): Boolean {
                    return lastNode != null
                }

                override fun next(): Node<T> {
                    val tmpNode = lastNode
                    lastNode = lastNode?.next
                    return tmpNode!!
                }
            }
        }
    }

    override fun iterator(): Iterator<T> = object : Iterator<T>{
        private var lastNode = head
        override fun hasNext(): Boolean {
            return lastNode != null
        }

        override fun next(): T {
            val tmpNode = lastNode
            lastNode = lastNode?.next
            return tmpNode?.key!!
        }

    }
}

