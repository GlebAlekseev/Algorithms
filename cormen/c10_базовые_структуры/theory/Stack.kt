package cormen.c10_базовые_структуры.theory

fun main(){

    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)
    println(stack)
    stack.pop()
    stack.pop()
    stack.pop()
    println(stack)
}

class Stack<T>{
    private var size = 8
    private var array = Array<Any?>(size){null} as Array<T?>
    private var top = -1 // Последний элемент

    fun push(element: T){
        if (isFull()) {
            size *= 2
            val newArray = Array<Any?>(size){null} as Array<T?>
            array.forEachIndexed { index, item ->
                newArray[index] = item
            }
            array = newArray
        }
        array[++top] = element
    }
    fun pop(): T {
        if (isEmpty()) throw RuntimeException("Underflow")
        return array[top--]!!
    }
    fun isEmpty(): Boolean {
        return top == -1
    }
    fun isFull(): Boolean {
        return size == top + 1
    }
    override fun toString(): String {
        var text = "["
        for (i in 0..top){
            text += if (i == 0) "${array[i]}," else if(i == top) " ${array[i]}" else " ${array[i]},"
        }
        text += "]"
        return text
    }
}