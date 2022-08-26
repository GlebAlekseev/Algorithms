package cormen.c6_7_8_сортировка.c6_heapsort.p7_4

import cormen.c6_7_8_сортировка.theory.Heap


fun main(){
    val arrayExample = arrayOf(5,13,2,25,7,17,20,8,4)
    val heap = Heap(arrayExample)
    println(heap)
    heap.heapSort()
    println(heap)
}