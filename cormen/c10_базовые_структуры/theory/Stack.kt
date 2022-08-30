package cormen.c10_базовые_структуры.theory

fun main(){

    val stack = Stack(5)
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

class Stack(val size: Int){
    private val array = Array(size){0}
    private var top = -1 // Последний элемент

    fun push(element: Int){
        if (isFull()) throw RuntimeException("Overflow")
        array[++top] = element
    }
    fun pop(): Int{
        if (isEmpty()) throw RuntimeException("Underflow")
        return array[top--]
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