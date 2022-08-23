package cormen.сортировка.heapsort.p7_3

import cormen.сортировка.theory.Heap

fun main(){

    val arrayExample = arrayOf(5,3,17,10,84,19,6,22,9)

    val heap = Heap(arrayExample)
    println(heap)
    heap.buildHeap()
    println(heap)
}
