package toff.containers.Queue

fun main() {
    val queue = Queue<Int>(5)
    queue.push(1)
    queue.pop()
    queue.push(2)
    queue.pop()
    queue.push(3)
    queue.pop()
    queue.push(4)
    queue.pop()
    queue.push(5)
    queue.push(6)
    queue.pop()
    queue.push(7)
    queue.pop()
    queue.push(8)
    queue.pop()
    queue.push(9)
    queue.pop()
    queue.push(10)
    queue.pop()
    queue.push(11)
    queue.pop()
    queue.push(12)
    queue.pop()
    queue.push(13)
    queue.push(14)
    queue.pop()
    queue.push(15)
    queue.pop()
    queue.push(16)
    queue.pop()
    queue.push(17)
    println(queue.pop())
    println(queue.pop())
    println(queue.pop())

}

class Queue<T>(k: Int) {
    val array = arrayOfNulls<Any>(k)
    var i = 0 // Позиция вставки
    var j = 0 // Позиция вынимания
    var size = 0
    fun push(value: T) {
        array[i] = value
        if (size == array.size) j = j.getIncValue() else size++
        i = i.getIncValue()
    }

    fun pop(): T {
        if (size == 0) throw RuntimeException("size = 0")
        val result = array[j]
        j = j.getIncValue()
        size--
        return result as T
    }

    private fun Int.getIncValue(): Int {
        if (this == array.size - 1) return 0
        return this + 1
    }
}

// Тут выделено O(k) памяти "val array = IntArray(k)"
// Т.к. размер фиксирован, нужно просто ходить по кругу, меняя только индексы