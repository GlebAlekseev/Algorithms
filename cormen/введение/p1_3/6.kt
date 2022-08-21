package cormen.введение.p1_3._6

fun main(){
    val arrayExample = arrayOf(5,2,4,6,1,3)
    println(arrayExample.toList())
    insertionSort(arrayExample)
    println(arrayExample.toList())

}


fun insertionSort(array: Array<Int>){
    for (j in 1 until array.size){
        val key = array[j]
        var i = j-1
        while (i >= 0 && array[i] > key){
            array[i+1] = array[i]
            i--
        }
        array[i+1] = key
    }
}

// Если использовать бинарный поиск для поиска места вставки, то алгоритм все равно останется в худшем случае О(n)
// Бинарный поиск будет небольшой оптимизацией, которая сократит количество операций array[i] > key
// Квадратичность сохранится, так как для успешной работы все равно требуется смещение всех элементов
// Если использовать связной список, то получится избежать копирования(смещения) элементов,
// однако в таком случае, доступ по индексу в произвольную позицию (середину для бин поиска) будет занимать не константное время.
