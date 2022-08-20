package cormen.введение.theory

fun main(){
    val arrayExample = arrayOf(3,41,52,26,38,57,9,49)
    println(arrayExample.toList())
    mergeSort(arrayExample,0,arrayExample.size-1)
    println(arrayExample.toList())

}

// Суть алгоритма в использовании слияния
// Частный случай - два отсортированных промежутка
// Слияние - метод сортировки для частного случая за O(n)
// Рекурсивный подход - способ использования слияния
// Операция слияния всегда O(n), значит алгоритм во всех случаях nlog(n)


fun mergeSort(array: Array<Int>, minIndex: Int, maxIndex: Int){
    // Если сортируемый промежуток больше 1, то сортировать, иначе выход.
    if (minIndex<maxIndex){
        // Разделяю на две части
        val mid = minIndex+(maxIndex+1-minIndex)/2
        mergeSort(array,minIndex,mid-1)
        mergeSort(array,mid,maxIndex)
        merge(array,minIndex,mid,maxIndex)
    }
}

fun merge(array: Array<Int>, minIndex: Int, mid: Int, maxIndex: Int){
    var firstIndex = minIndex
    var secondIndex = mid
    var index = 0
    val newArray = Array(maxIndex-minIndex+1){0}
    while (firstIndex <= mid-1 && secondIndex <= maxIndex){
        newArray[index] = if(array[firstIndex] <= array[secondIndex]){
            array[firstIndex++]
        }else{
            array[secondIndex++]
        }
        index++
    }

    if (firstIndex > mid-1 && secondIndex > maxIndex){
        // Если промежутки равные, то остатка нет
    } else if (firstIndex > mid-1) {
        // Все элементы начиная с secondIndex по maxIndex добавляются в newArray[index]
        for (m in secondIndex..maxIndex){
            newArray[index++] = array[m]
        }
    } else if (secondIndex > maxIndex){
        // Все элементы начиная с firstIndex по mid-1 добавляются в newArray[index]
        for (m in firstIndex..mid-1){
            newArray[index++] = array[m]
        }
    }

    // copy from tmp
    newArray.forEachIndexed { index, it ->
        array[minIndex+index] = it
    }
}

// Нижняя и верхняя граница nlog(n)
