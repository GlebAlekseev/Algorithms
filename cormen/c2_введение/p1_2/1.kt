package cormen.c2_введение.p1_2

fun main(){
    val arrayExample = arrayOf(10,9,8,7,6,5,4,3,2,1)
    println(arrayExample.toList())
    selectionSort(arrayExample)
    println(arrayExample.toList())

}

// Сортировка выбором минимального/максимального элемента
// Найти минимальный элемент
// Сдвинуть массив
// Найдя минимальный элемент всю левую часть сдвигать вправо, замещая позицию минимального элемента.
// Сортировка возможна сразу при поступлении (in place) (без доп памяти)
// Обходить можно в обе стороны
// В отсортированном массиве минимум найдется только один раз
// В отсортированном массиве minIndex >= i будет срабатывать раз


fun selectionSort(array: Array<Int>){
    var i = 0                                       // c1 1
    var j = array.size-1                            // c2 1
    while (j>=0){                                   // c3 n+1
        var minValue = Int.MAX_VALUE                // c4 n
        var minIndex = 0                            // c5 n
        for (m in i..array.size-1){           // c6 ∑n-1|j=0 (j)
            if (minValue > array[m]){               // c7 ∑n-1|j=0 (j - 1)
                minValue = array[m]                 // c8 ∑n-1|j=0 (j - 1)
                minIndex = m                        // c9 ∑n-1|j=0 (j - 1)
            }
        }
        minIndex--                                  // c10 n
        while (minIndex >= i){                      // c11 ∑n-1|j=0 (j)
            array[minIndex+1] = array[minIndex]     // c12 ∑n-1|j=0 (j - 1)
            minIndex--                              // c13 ∑n-1|j=0 (j - 1)
        }
        array[i] = minValue                         // c14 n
        i++                                         // c15 n
        j--                                         // c16 n
    }
}


// Формула арифметической прогрессии
// кол-во элементов*(первый + последний)/2 =  ∑n|j=1 = n*(1 + n)/2


// ∑n-1|j=0 (j)       =    n*(0+n-1)/2     = n(n-1)/2
// ∑n-1|j=0 (j - 1)   =    n*(-1+n-2)/2    = n(n-3)/2

// Суммарное число операций
// T(n) = c1 + c2 + c3(n+1) + c4n + c5n + c10n + c14n + c15n + c16n +
//          c6 ∑n-1|j=0 (j) + c7 ∑n-1|j=0 (j - 1) + c8 ∑n-1|j=0 (j - 1) + c9 ∑n-1|j=0 (j - 1) +
//          c11 ∑n-1|j=0 (j) + c12 ∑n-1|j=0 (j - 1) + c13 ∑n-1|j=0 (j - 1)


// Нижняя граница
// В лучшем случае c8 c9 не будут выполняться, квадратичность останется, тк c6+c7 = n^2
// Также c12 с13 отбросятся, и c11 ∑n-1|j=0 (1) константа Tj
// Квадратично

// Верхняя граница
// Худший случай
// Квадратично
