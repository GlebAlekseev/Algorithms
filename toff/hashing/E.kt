package toff.hashing

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.pow

fun main() {
    readLine()!!
    val integers1 = readLine()!!.split(" ").map { it.toInt() }
    readLine()!!
    val integers2 = readLine()!!.split(" ").map { it.toInt() }

    val setHashing1 = SetHashing(integers1)
    val setHashing2 = SetHashing(integers2)

    // O(n^2) получить мапу с длинами префиксов
    val map1 = mutableMapOf<Int, MutableList<Long>>()
    for (i in 1..integers1.size) {
        for (j in i downTo 1) {
            //j..i
            val last = map1[i - j + 1] ?: mutableListOf()
            last.add(setHashing1.getHashSubArray(j, i))
            map1[i - j + 1] = last
        }
    }

    // O(n^2) получить мапу с длинами префиксов
    val map2 = mutableMapOf<Int, MutableList<Long>>()
    for (i in 1..integers2.size) {
        for (j in i downTo 1) {
            //j..i
            val last = map2[i - j + 1] ?: mutableListOf()
            last.add(setHashing2.getHashSubArray(j, i))
            map2[i - j + 1] = last
        }
    }

    var maxResult = 0L
    map1.keys.toList().reversed().forEach { count->
        if (!map2.containsKey(count)) return@forEach
        if (maxResult != 0L) return@forEach
        val list1 = map1[count]!!
        val list2 = map2[count]!!
        list1.forEach { item1 ->
            list2.forEach {  item2 ->
                if (item1 == item2){
                    maxResult = count.toLong()
                    return@forEach
                }
            }
        }
    }
    println(maxResult)
}

// Найти самый длинный общий подотрезок
// Максимальная длина общего множества между двумя последовательностями цифр

class SetHashing(private val integers: Collection<Int>) {
    var p = (2.0.pow(63) - 1).toLong()
    var k = 100001
    private val hashAmounts = mutableListOf(0L)

    init {
        assert(integers.isNotEmpty())
        integers.forEachIndexed { index, value ->
            val newValue = (hashAmounts.last() + power(k, value, p) % p) % p
            hashAmounts.add(newValue)
        }
    }

    fun getHashSubArray(l: Int, r: Int): Long = (hashAmounts[r] - hashAmounts[l - 1] + p) % p
}

fun power(value: Int, pow: Int, p: Long): Long {
    var result = 1L
    var tempValue = value.toLong()
    var tempPow = pow.toLong()
    while (tempPow > 0) {
        if (tempPow % 2L == 1L) {
            result = ((result % p)*(tempValue % p)) % p
        }
        tempValue = ((tempValue % p) * (tempValue % p)) % p
        tempPow = tempPow / 2
    }
    return result
}


class Test1 {
    @Test
    fun test1() {
        val integers = listOf(1, 2, 3, 4, 5, 3, 2, 1, 1)
        val setHashing = SetHashing(integers)
        val res1 = setHashing.getHashSubArray(1, 3)
        val res2 = setHashing.getHashSubArray(6, 9)
        println(">>>> res1=$res1\n\tres2=$res2")
    }

}

@RunWith(Parameterized::class)
class Test2(
    private val text1: String,
    private val text2: String,
    private val expected: Int
) {
    @Test
    fun test() {
        println(">>>>>> \n\t\ttext1=\t$text1 \n\t\ttext2=\t$text2")
        val integers1 = text1.split(" ").map { it.toInt() }
        val integers2 = text2.split(" ").map { it.toInt() }

        val setHashing1 = SetHashing(integers1)
        val setHashing2 = SetHashing(integers2)

        // O(n^2) получить мапу с длинами префиксов
        val map = mutableMapOf<Int, MutableList<Long>>()
        for (i in 1..integers1.size) {
            for (j in i downTo 1) {
                //j..i
                val last = map[i - j + 1] ?: mutableListOf()
                last.add(setHashing1.getHashSubArray(j, i))
                map[i - j + 1] = last
            }
        }

        var maxResult = 0L
        for ((count, list) in map) {
//            println(">> count=$count list=$list")
            for (hash in list) {
//                println("\t\thash=$hash")
                for (i in 1..integers2.size) {
                    for (j in i downTo 1) {
                        //j..i
//                        println("\t\t\t$j..=$i")
                        val currentHash = setHashing2.getHashSubArray(j, i)
                        if (currentHash == hash) {
//                            println("hasshes equals cpunt=$count")
                            if (count > maxResult){
                                maxResult = count.toLong()
                            }
                        }
                    }
                }
            }
        }
        println("result=${maxResult} expected=$expected")

        Assert.assertEquals(expected, maxResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            val test1 = StringBuilder("1")
                repeat(200){
                    test1.append(" 2 3 4 5 6")
                }
            return listOf(
//                arrayOf("1 2 3", "3 2 1", 3),
//                arrayOf("1 2 3", "4 5 6", 0),
//                arrayOf("1", "1", 1),
//                arrayOf("1 2 3", "3 3 2 1", 3),
                arrayOf("8888 1111 51 52999 99953", "53 53 1111 8888 2 9951", 1),
//                arrayOf(test1.toString(), test1.toString(), 1),
            )
        }
    }
}