package cormen.c6_7_8_сортировка.c6_heapsort.p7_2

import cormen.c6_7_8_сортировка.theory.Heap


fun main(){
    val arrayExample = arrayOf(27,17,3,16,13,10,1,5,7,12,4,8,9,0)
    println(arrayExample.toList())
    heapify(arrayExample,2)
    println(arrayExample.toList())
}

// Получить для индекса i индексы потомков
// Проверить на выход за пределы для левого и правого
// Выбрать индекс наибольшего среди родителя и потомков
// Если потомок больше родителя, то свап
// Устранить побочные эффекты вызвав рекурсивно для переставленного потомка

fun heapify(array: Array<Int>,i: Int){
    val left = Heap.left(i)
    val right = Heap.right(i)
    var largest = 0
    if (left<array.size && array[left]>array[i]){
        largest = left
    }else{
        largest = i
    }

    if (right<array.size && array[right]>array[largest]){
        largest = right
    }
    if (largest!=i){
        array[i] = array[largest].also { array[largest] = array[i] }
        heapify(array, largest)
    }
}