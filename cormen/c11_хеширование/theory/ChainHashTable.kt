package cormen.c11_хеширование.theory

import cormen.c10_базовые_структуры.theory.DLinkedList

fun main(){

    val chainHashTable = ChainHashTable<String,Int>(4)
    val key1 = "2134"
    val key2 = "3431"
    val key3 = "7556"
    val key4 = "9577"
    val key5 = "6527"
    val key6 = "1521"
    chainHashTable.insert(key1,1)
    chainHashTable.insert(key2,2)
    chainHashTable.insert(key3,3)
    chainHashTable.insert(key4,4)
    chainHashTable.insert(key5,5)
    chainHashTable.insert(key6,6)

    println("search $key1 = ${chainHashTable.get(key1)}")
    println("search $key2 = ${chainHashTable.get(key2)}")
    println("search $key3 = ${chainHashTable.get(key3)}")
    println("search $key4 = ${chainHashTable.get(key4)}")
    println("search $key5 = ${chainHashTable.get(key5)}")
    println("search $key6 = ${chainHashTable.get(key6)}")

    println("size=${chainHashTable.size} capacity=${chainHashTable.capacity}")


    chainHashTable.delete(key1)
    chainHashTable.delete(key2)
    chainHashTable.delete(key3)
    chainHashTable.delete(key4)
    chainHashTable.delete(key4)
    chainHashTable.delete(key4)

    try {
        chainHashTable.delete(key1)
        chainHashTable.delete(key1)
    }catch (e: RuntimeException){
        println("Ошибка: $e")
    }


}

// Хеш-таблица, использующая метод цепочек
// TODO для хранения внутри цепочек при достижении значительного числа элементов (~32) заменять их на BST (Binary Search Tree)
class ChainHashTable<KEY, VALUE>(private var _capacity: Int): AssociativeArray<KEY, VALUE>{
    val capacity
        get() = _capacity
    private var m = capacity
    private var array = Array<DLinkedList<Pair<KEY, VALUE>>?>(capacity){ null }
    private var _size = 0
    val size
        get() = _size
    // Всегда одинаковые значения для одного ключа
    // Для допустимого ключа на выходе должна давать натуральное число в заданном диапазоне
    // [-] Желательно, чтобы ключ с равной вероятностью хэшировался в случайную ячейку
    // O(1)
    private fun getHash(value: KEY): Int{
        return value.hashCode() % m
    }

    private fun checkOverflow(){
        if (size >= capacity*4/3){
            resize(2*capacity)
        }
    }

    fun resize(newCapacity: Int){
        _capacity = newCapacity
        m = capacity
        val newArray =  Array<DLinkedList<Pair<KEY, VALUE>>?>(capacity){ null }
        _size = 0
        array.forEach {
            it?.forEach{
                val hashedKey = getHash(it.first)
                if (newArray[hashedKey] == null){
                    newArray[hashedKey] = DLinkedList()
                }
                newArray[hashedKey]!!.pushBack(it)
                _size++
            }
        }
        array = newArray
    }

    // O(1)
    override fun insert(key: KEY, value: VALUE){
        val hashedKey = getHash(key)
        if (array[hashedKey] == null){
            array[hashedKey] = DLinkedList()
        }
        array[hashedKey]!!.pushBack(Pair(key,value))
        _size++
        checkOverflow()
    }

    // O(1) - O(n)
    override fun delete(key: KEY){
        if (_size == 0) throw RuntimeException("Таблица пустая")
        val hashedKey = getHash(key)
        if (array[hashedKey] == null) throw RuntimeException("Элемент не найден")
        array[hashedKey]?.forEach { pair ->
            if (key == pair.first) array[hashedKey]!!.removeOfValue(pair)
            if (array[hashedKey]?.size == 0) array[hashedKey] = null
        }
        _size--
    }

    // O(1) - O(n)
    override fun get(key: KEY): VALUE?{
        if (_size == 0) return null
        val hashedKey = getHash(key)
        if (array[hashedKey] == null) return null
        array[hashedKey]?.forEach { (_key, value) ->
            if (key == _key) return value
        }
        return null
    }

}