package cormen.c6_7_8_сортировка.c7_quicksort.p7_1._2

// Для сортировки в невозрастающем порядке требуется n итераций в сумме для quicksort
// Если сделать проверку O(n) для частного случая, то можно уменьшить время с n до log(n)

fun partition(array: Array<Int>, low: Int, high: Int): Int{
    // Проверка на невозрастание
    var isDown = true
    for (n in low..high-1){
        if (array[n] < array[n+1]){
            isDown = false
        }
    }
    if (isDown){
        val index = (low+high)/2
        array[high] = array[index].also { array[index] = array[high] }
    }
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