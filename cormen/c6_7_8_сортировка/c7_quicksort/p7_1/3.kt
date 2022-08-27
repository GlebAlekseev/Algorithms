package cormen.c6_7_8_сортировка.c7_quicksort.p7_1._1

fun main(){

}


// Данная функция относительно входных данных low..high, выполняется за O(n)
// O(n) из-за цикла for (j in low..high-1)
fun partition(array: Array<Int>, low: Int, high: Int): Int{
    val pivot = array[high]
    var i = low-1
    for (j in low..high-1){                                     // n
        if (array[j] <= pivot){                                      // n-1
            i++
//            print("swap i=$i(${array[i]}) and j=$j(${array[j]})")
            array[i] = array[j].also { array[j] = array[i] }         // n-1
        }
        println(" j=$j array=${array.toList()}")
    }
    array[i+1] = array[high].also { array[high] = array[i+1] }
    return i+1
}