package cormen.сортировка.heapsort.p7_4

import cormen.сортировка.theory.Heap


fun main(){
    val arrayExample = arrayOf(5,13,2,25,7,17,20,8,4)
    val heap = Heap(arrayExample)
    println(heap)
    heap.heapSort()
    println(heap)
}