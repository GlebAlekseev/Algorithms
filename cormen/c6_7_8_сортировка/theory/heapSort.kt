package cormen.c6_7_8_сортировка.theory

fun main(){
//    val arrayExample = arrayOf(27,17,3,16,13,10,1,5,7,12,4,8,9,0)
//    val arrayExample = arrayOf(5,3,17,10,84,19,6,22,9)
    val arrayExample = arrayOf(5,13,2,25,7,17,20,8,4)
    val heap = Heap(arrayExample)
//    println(heap)
//    heap.buildHeap()
    println(heap)
    heap.heapSort()
    println(heap)
//    heap.heapify(2)
//     println(heap)
}

class Heap(val array: Array<Int>){
    private var heapsize = 0
    init {
        heapsize = array.size
    }
    override fun toString(): String {
        return array.toList().toString()
    }

    // Позволяет поддерживать основное свойство (Родитель больше потомка).
    // O(log(n))
    // Для текущего индекса проверяет максимальность, свапает и заботится о побочном эффекте
    fun heapify(i: Int){
        val left = left(i)
        val right = right(i)
        var largest = 0
        if (left<heapsize && array[left]>array[i]){
            largest = left
        }else{
            largest = i
        }

        if (right<heapsize && array[right]>array[largest]){
            largest = right
        }
        if (largest!=i){
            array[i] = array[largest].also { array[largest] = array[i] }
            heapify(largest)
        }
    }

    // Строит кучу из исходного (неотсортированного) массива
    // O(n)
    fun buildHeap(){
        for (i in array.size/2-1 downTo 0){
            heapify(i)
        }

    }


    // Сортирует массив, не используя доп памяти.
    // O(nlog(n))
    fun heapSort(){
        // Делаю из массива кучу
        buildHeap()
        // Сортирую
        sort()
    }

    fun sort(){
        for(i in array.size-1 downTo 1){
            array[0] = array[i].also { array[i] = array[0] }
            heapsize--
            heapify(0)
        }
    }

    // Моделирование очереди с приоритетом на основе кучи
    // Взятие наибольшего
    // O(log(n))
    fun extractMax(): Int{
        if (heapsize < 1) throw RuntimeException("no elements")
        val max = array[0]
        array[0] = array[heapsize-1]
        heapsize--
        heapify(0)
        return max
    }

    // Моделирование очереди с приоритетом на основе кучи
    // Добавление элемента
    // O(log(n))
    fun insert(key: Int){
        if (heapsize==array.size) throw RuntimeException("array is full")
        heapsize++
        array[heapsize-1] = Int.MIN_VALUE
        increaseKey(heapsize-1,key)
    }

    // Моделирование очереди с приоритетом на основе кучи
    // Получение наибольшего
    // O(1)
    fun maximum(): Int{
        return array[0]
    }

    // Проверяем ключ на увеличение*
    // После установки значения убрать побочный эффект, а именно
    // Если родитель меньше нового увеличенного числа, то поднимаем его вплоть до родителя
    // Своего рода Heapify, только вверх
    // O(log(n))
    fun increaseKey(i: Int, key: Int){
        if (key < array[i]) throw RuntimeException("new key is smaller than the current one")
        array[i] = key
        var j = i
        while (j > 0 && array[parent(j)] < array[j]){
            array[j] = array[parent(j)].also { array[parent(j)] = array[j] }
            j = parent(j)
        }
    }



    companion object{
        fun parent(i: Int): Int = (i+1)/2
        fun left(i: Int): Int = 2*i + 1
        fun right(i: Int): Int = 2*i + 2
    }

}