package cormen.введение.p1_3

//  insertionSort переписать используя рекурсию

fun main() {
    val arrayExample = arrayOf(5, 2, 4, 6, 1, 3)
    println(arrayExample.toList())
    insertionSort(arrayExample)
    println(arrayExample.toList())

}

// Точка входа в метод сортировки
fun insertionSort(array: Array<Int>) {
    insertionSort(array, 1, array.size - 1)
}

// Рекурсивный метод, который заменяет цикл
// Условие minIndex <= maxIndex, аналогично обходу циклом диапазона
private fun insertionSort(array: Array<Int>, minIndex: Int, maxIndex: Int) {
    if (minIndex <= maxIndex) {
        insert(array,minIndex)
        insertionSort(array, minIndex + 1, maxIndex)
    }
}

// Метод сдвигает текущий элемент влево, согласно условию array[i] > key
private fun insert(array: Array<Int>, currentIndex: Int) {
    val key = array[currentIndex]
    var i = currentIndex - 1
    while (i >= 0 && array[i] > key) {
        array[i + 1] = array[i]
        i--
    }
    array[i + 1] = key
}