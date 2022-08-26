package cormen.c6_7_8_сортировка.c6_heapsort.p7_2

import cormen.c6_7_8_сортировка.theory.Heap


fun main(){
    val arrayExample = arrayOf(27,17,3,16,13,10,1,5,7,12,4,8,9,0)
    println(arrayExample.toList())
    heapifyLoop(arrayExample,2)
    println(arrayExample.toList())
}

// Замена рекурсии циклом

fun heapifyLoop(array: Array<Int>,i: Int){
    var iteI = i
    while(true){
        val left = Heap.left(iteI)
        val right = Heap.right(iteI)
        var largest = 0

        if (left<array.size && array[left]>array[iteI]){
            largest = left
        }else{
            largest = iteI
        }

        if (right<array.size && array[right]>array[largest]){
            largest = right
        }
        if (largest!=iteI){
            array[iteI] = array[largest].also { array[largest] = array[iteI] }
            iteI = largest
        }else{
            break
        }
    }
}