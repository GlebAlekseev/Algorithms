package cormen.c2_введение.p1_3


fun main(){
    val arrayExample = arrayOf(3,41,52,26,38,57,9,49)
    println(arrayExample.toList())
    mergerSort(arrayExample)
    println(arrayExample.toList())

}

fun mergerSort(array: Array<Int>){
    cormen.c2_введение.theory.mergeSort(array,0,array.size-1)
}