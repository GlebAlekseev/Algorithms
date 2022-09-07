package cormen.c11_хеширование.theory

import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

fun main(){
    val openAddressingHashTable = OpenAddressingHashTable<String,Int>(6).apply {
        probingType = OpenAddressingHashTable.Companion.Probing.QUADRATIC
    }
    val key1 = "2134"
    val key2 = "3431"
    val key3 = "7556"
    val key4 = "9577"
    val key5 = "6527"
    val key6 = "1521"
    openAddressingHashTable.insert(key1,1)
    openAddressingHashTable.insert(key2,2)
    openAddressingHashTable.insert(key3,3)
    openAddressingHashTable.insert(key4,4)
    openAddressingHashTable.insert(key5,5)
    openAddressingHashTable.insert(key6,6)

    println("search $key1 = ${openAddressingHashTable.get(key1)}")
    println("search $key2 = ${openAddressingHashTable.get(key2)}")
    println("search $key3 = ${openAddressingHashTable.get(key3)}")
    println("search $key4 = ${openAddressingHashTable.get(key4)}")
    println("search $key5 = ${openAddressingHashTable.get(key5)}")
    println("search $key6 = ${openAddressingHashTable.get(key6)}")

    println("size=${openAddressingHashTable.size} capacity=${openAddressingHashTable.capacity}")

    openAddressingHashTable.delete(key1)
    openAddressingHashTable.delete(key2)
    openAddressingHashTable.delete(key3)
    openAddressingHashTable.delete(key4)
    openAddressingHashTable.delete(key5)
    openAddressingHashTable.delete(key6)
    println("search $key6 = ${openAddressingHashTable.get(key6)}")
    try {
        openAddressingHashTable.delete(key1)
    }catch (e: RuntimeException){
        println("Ошибка: $e")
    }
}

// Хеш-таблица с открытой адресацией


class OpenAddressingHashTable<KEY, VALUE>(_capacity: Int,var probingType: Probing = Probing.LINEAR): AssociativeArray<KEY, VALUE>{
    val capacity = when (probingType) {
        Probing.LINEAR -> _capacity
        Probing.QUADRATIC -> getSquareCapacity(_capacity)
    }
    private fun getSquareCapacity(value: Int): Int{
        val result = sqrt(value.toDouble())
        val result2 = result - result.toInt()
        if (result2 == 0.0){
            return value
        }
        return (result.toInt() + 1).toDouble().pow(2.0).toInt()
    }
    private val m = capacity
    private val array = Array<Pair<KEY, VALUE>?>(capacity){ null }
    private var _size = 0
    val size
        get() = _size

    private fun getHash(value: KEY): Int{
        return value.hashCode() % m
    }
    // O(1) - O(n)
    override fun insert(key: KEY, value: VALUE) {
        if (size == capacity) throw RuntimeException("Таблица заполнена")
        val hashedKey = getHash(key)
        val index = when (probingType) {
            Probing.LINEAR -> linearProbing(hashedKey,null)
            Probing.QUADRATIC -> quadraticProbing(hashedKey,null)
        }
        if (index == -1) throw RuntimeException("В таблице нет свободных мест")
        array[index] = Pair(key, value)
        _size++
    }
    // O(1) - O(n)
    override fun delete(key: KEY) {
        if (_size == 0) throw RuntimeException("Таблица пустая")
        val index = find(key)
        if (index == -1) throw RuntimeException("Элемент не найден")
        array[index] = null
        _size--
    }
    // O(1) - O(n)
    override fun get(key: KEY): VALUE? {
        if (_size == 0) return null
        val index = find(key)
        if (index == -1) return null
        return array[index]?.second
    }

    private fun find(key: KEY): Int{
        val hashedKey = getHash(key)
        return when (probingType) {
            Probing.LINEAR -> linearProbing(hashedKey,key)
            Probing.QUADRATIC -> quadraticProbing(hashedKey,key)
        }
    }

    // Линейный пробинг
    private fun linearProbing(startIndex: Int, target: KEY?): Int{
        // Обойти array с индекса полный круг пока не найду null пустое
        // Если пустого нет то исключение
        var i = startIndex
        while (true){
            var prev = startIndex - 1
            if (prev == -1) prev = capacity-1
            if (i == capacity) i = 0
            if(array[i]?.first == target) return i
            if (i == prev) break
            i++
        }
        return -1
    }
    // Квадратичный пробинг
    private fun quadraticProbing(startIndex: Int, target: KEY?): Int{
        var i = startIndex
        var k = 1
        while (k<=capacity){
            if(i >= capacity) i %= m
            if(array[i]?.first == target) return i
            i += (k+k*k)/2
            k++
        }
        return -1
    }

    companion object{
        enum class Probing{
            LINEAR,
            QUADRATIC
        }
    }
}