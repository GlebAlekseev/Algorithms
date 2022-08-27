package cormen.c6_7_8_сортировка.c7_quicksort.p7_1

fun main(){
    val arrayExample = arrayOf(2,2,2,2,2,2,2,2,2,2,2)
    println(arrayExample.toList())
    quickSort(arrayExample, 0, arrayExample.size - 1)
    println(arrayExample.toList())
}

// Если массив состоит из одинаковых элементов, то внутри partition в цикле каждый раз будет
// Будет срабатывать условие array[j]<=pivot, из-за чего массив меньших или равных пивоту элеметов увеличится
// Вернется индекс последнего элемента из диапазона меньших + 1
//


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
    val pivot = array[high]
    var i = low-1
    var isOneType = true
    for (j in low..high-1){
        if (array[j] <= pivot){
            if (array[j] != array[j+1]) isOneType = false
            i++
//            print("swap i=$i(${array[i]}) and j=$j(${array[j]})")
            array[i] = array[j].also { array[j] = array[i] }
        }
        println(" j=$j array=${array.toList()}")
    }
    array[i+1] = array[high].also { array[high] = array[i+1] }
    if (isOneType) return (low+high)/2
    return i+1
}