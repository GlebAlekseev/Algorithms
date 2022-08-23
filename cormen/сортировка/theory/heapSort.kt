package cormen.сортировка.theory

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
    fun extractMax(){

    }

    // Моделирование очереди с приоритетом на основе кучи
    // Добавление элемента
    // O(log(n))
    fun insert(){

    }


    companion object{
        fun parent(i: Int): Int = (i+1)/2
        fun left(i: Int): Int = 2*i + 1
        fun right(i: Int): Int = 2*i + 2
    }

}