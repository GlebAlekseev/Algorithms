package cormen.c6_7_8_сортировка.theory

fun main(){
//    val arrayExample = arrayOf(13,9,9,5,12,8,7,4,21,2,6,11)
    val arrayExample = arrayOf(8,7,6,5,4,4,3,2)
    println(arrayExample.toList())
    quickSort(arrayExample,0,arrayExample.size-1)
    println(arrayExample.toList())
}


// Условие выхода: high - low == 1
fun quickSort(array: Array<Int>, low: Int, high: Int){
    println("called quickSort with low:$low high:$high")
    if (low < high){
        val mid = partition(array,low,high)
        println("mid:$mid")
        quickSort(array,low,mid-1)
        quickSort(array,mid+1,high)
    }
}

fun partition(array: Array<Int>, low: Int, high: Int): Int{
    val pivot =  array[high]

    var i = low-1
    for (j in low..high-1){
        if (array[j] <= pivot){
            i++
//            print("swap i=$i(${array[i]}) and j=$j(${array[j]})")
            array[i] = array[j].also { array[j] = array[i] }
        }
        println(" j=$j array=${array.toList()}")
    }

    array[i+1] = array[high].also { array[high] = array[i+1] }
    return i+1
}