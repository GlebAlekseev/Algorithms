package cormen.введение.p1_3


// Merge
// Всегда отрабатывает за O(n)
// Получает на вход два диапазона и массив
// Будут созданы два указателя, для каждого диапазона
// Затем итерирование этих указателей до дех пор, пока один из них не зайдет за предел диапазона
// На каждой итерации выбирать минимальное значение, его указатель инкрементировать

// Когда итерирование завершится, возможны три исхода
// 1 - оба вышли за диапазон (одинаковый размер диапазона) (ничего не делать)
// 2 - только первый вышел за пределы, значит он короче,
// поэтому остаток начиная со второго указателя и до конца диапазона вносится в конец результирующего
// 3 - аналогично второму случаю, для второго указателя

// Перенести результат из временного массива в исходный
// Временный массив нужен, для правильной работы итерирования выше

private fun merge(array: Array<Int>, minIndex: Int, mid: Int, maxIndex: Int){
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