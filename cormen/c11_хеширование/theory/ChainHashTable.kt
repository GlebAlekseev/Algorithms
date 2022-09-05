package cormen.c11_хеширование.theory

import cormen.c10_базовые_структуры.theory.DLinkedList

fun main(){

    val chainHashTable = ChainHashTable(10)
    val key1 = 2134
    val key2 = 3431
    val key3 = 7556
    val key4 = 9577
    chainHashTable.insert(key1,1)
    chainHashTable.insert(key2,2)
    chainHashTable.insert(key3,3)
    chainHashTable.insert(key4,4)

    println("search $key1 = ${chainHashTable.search(key1)}")
    println("search $key2 = ${chainHashTable.search(key2)}")
    println("search $key3 = ${chainHashTable.search(key3)}")
    println("search $key4 = ${chainHashTable.search(key4)}")

    println(chainHashTable.size)

    chainHashTable.delete(key1)
    chainHashTable.delete(key2)
    chainHashTable.delete(key3)
    chainHashTable.delete(key4)

    try {
        chainHashTable.delete(key1)
    }catch (e: RuntimeException){
        println("Ошибка: $e")
    }

}

// Хеш-таблица, использующая метод цепочек
// TODO для хранения внутри цепочек при достижении значительного числа элементов (~32) заменять их на BST

class ChainHashTable(val capacity: Int){
    private val m = capacity
    private val array = Array<DLinkedList<Pair<Int,Int>>?>(capacity){null}
    private var _size = 0
    val size
        get() = _size
    // Всегда одинаковые значения для одного ключа
    // Для допустимого ключа на выходе должна давать натуральное число в заданном диапазоне
    // [-] Желательно, чтобы ключ с равной вероятностью хэшировался в случайную ячейку
    private fun getHash(value: Int): Int{
        return value % m
    }

    fun insert(key: Int, value: Int){
        if (_size == capacity) throw RuntimeException("Таблица переполнена")
        val hashedKey = getHash(key)
        if (array[hashedKey] == null){
            array[hashedKey] = DLinkedList()
        }
        array[hashedKey]!!.pushBack(Pair(key,value))
        _size++
    }

    fun delete(key: Int){
        if (_size == 0) throw RuntimeException("Таблица пустая")
        val hashedKey = getHash(key)
        if (array[hashedKey] == null) throw RuntimeException("Элемент не найден")
        array[hashedKey]?.forEach { pair ->
            if (key == pair.first) array[hashedKey]!!.removeOfValue(pair)
        }
        _size--
    }

    fun search(key: Int): Int?{
        if (_size == 0) return null
        val hashedKey = getHash(key)
        if (array[hashedKey] == null) return null
        array[hashedKey]?.forEach { (_key, value) ->
            if (key == _key) return value
        }
        return null
    }

}