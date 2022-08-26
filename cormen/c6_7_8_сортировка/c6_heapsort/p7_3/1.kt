package cormen.c6_7_8_сортировка.c6_heapsort.p7_3

import cormen.c6_7_8_сортировка.theory.Heap

fun main(){

    val arrayExample = arrayOf(5,3,17,10,84,19,6,22,9)

    val heap = Heap(arrayExample)
    println(heap)
    heap.buildHeap()
    println(heap)
}
