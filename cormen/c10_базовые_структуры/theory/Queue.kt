package cormen.c10_базовые_структуры.theory

fun main(){
    val queue = Queue(5)
    println(queue)
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    queue.enqueue(5)
    println(queue)
    queue.decueue()
    queue.decueue()
    queue.decueue()
    println(queue)
    queue.enqueue(6)
    queue.enqueue(7)
    queue.enqueue(8)
    queue.decueue()
    queue.decueue()
    queue.decueue()
    queue.decueue()
    println(queue)


}

class Queue(val size: Int): Iterable<Int> {
    private var head = 0
    private var tail = 0
    private var queueSize = 0
    private val array = Array(size){0}

    fun enqueue(element: Int){
        if (isFull()) throw RuntimeException("Overflow")
        queueSize++
        array[tail] = element
        if (tail == size-1) tail = 0 else tail++
    }
    fun decueue(): Int{
        if (isEmpty()) throw RuntimeException("Underflow")
        queueSize--
        val last = head
        if (head == size-1) head = 0 else head++
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