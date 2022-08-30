package cormen.c10_базовые_структуры.theory

fun main(){
    val deque = Deque(5)
    deque.enqueueBack(6)
    deque.enqueueBack(7)
    deque.enqueueBack(8)
    deque.enqueueBack(9)
    deque.enqueueBack(10)
    println(deque)
    deque.decueueFront()
    deque.decueueFront()
    deque.decueueBack()
    deque.decueueBack()
    println(deque)
    deque.enqueueBack(11)
    println(deque)

}

class Deque(val size: Int): Iterable<Int> {
    private var head = 0
    private var tail = 0
    private var queueSize = 0
    private val array = Array(size){0}

    fun enqueueFront(element: Int){
        if (isFull()) throw RuntimeException("Overflow")
        queueSize++
        array[tail] = element
        if (tail == size-1) tail = 0 else tail++
    }
    fun decueueFront(): Int{
        if (isEmpty()) throw RuntimeException("Underflow")
        queueSize--
        val last = head
        if (head == size-1) head = 0 else head++
        return array[last]
    }

    fun enqueueBack(element: Int){
        if (isFull()) throw RuntimeException("Overflow")
        queueSize++
        if (head == size-1) head = 0 else head++
        array[head] = element
    }
    fun decueueBack(): Int{
        if (isEmpty()) throw RuntimeException("Underflow")
        queueSize--
        val last = tail
        if (tail == size-1) tail = 0 else tail++
        return array[last]
    }

    fun isEmpty(): Boolean {
        return queueSize == 0
    }
    fun isFull(): Boolean {
        return queueSize == size
    }

    override fun iterator(): Iterator<Int> = object : Iterator<Int> {
        var current = 0
        override fun hasNext(): Boolean {
            return current < queueSize
        }
        override fun next(): Int {
            var index = if (head + current >= size) current - (size-head) else head + current
            current++
            return array[index]
        }
    }
    override fun toString(): String {
        var text = "["
        this.forEachIndexed{ i, it ->
            text += if (queueSize == 1) "${it}" else if(i == queueSize-1) " ${it}" else if (i == 0) "${it}," else " ${it},"
        }
        text += "]"
        return text
    }
}