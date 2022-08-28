package cormen.c6_7_8_сортировка.theory

fun main(){
    val arrayExample = arrayOf(6,0,2,0,1,3,4,6,1,3,2)

    println(arrayExample.toList())
    println(countingSort(arrayExample).toList())
}

// O(n+k), где k = maxElementValue
fun countingSort(arrayExample: Array<Int>): Array<Int>{
    val arrayResult = Array(arrayExample.size){0}           // n
    val maxElementValue = arrayExample.maxOf { it }         // n
    val arrayTemp = Array(maxElementValue+1){0}        // maxElementValue = k

    arrayExample.forEach {                             // n
        arrayTemp[it]++
    }

    for (i in 1 until arrayTemp.size){                 // maxElementValue = k
        arrayTemp[i] +=arrayTemp[i-1]
    }

    // Обход по убыванию поддерживает стабильность алгоритма
    for (j in arrayTemp.size-1 downTo 0){              //  maxElementValue = k
        val it = arrayExample[j]
        arrayResult[arrayTemp[it]-1] = it
        arrayTemp[it]--
    }

    // Если идти по возрастанию, то алгоритм потеряет стабильность
    //    arrayExample.forEach {
    //        arrayResult[arrayTemp[it]-1] = it
    //        arrayTemp[it]--
    //    }

    // Каждый раз мы смотрим индекс для значения в arrayTemp, после чего индекс уменьшаем,
    // то есть значения расставляем с высоких к низким индексам (нужен обход стремящийся к нулю)
    // Если обходить с начала, то относительно исходной последовательности, порядок не будет сохраняться,
    // из-за чего нарушится стабильность


    return arrayResult
}