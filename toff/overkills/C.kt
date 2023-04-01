package toff.overkills

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import kotlin.math.pow
import kotlin.random.Random

fun main() {
    val t = readLine()!!.toInt()
    val maxValue = 10.0.pow(18).toULong()
    val fibonacciMultipliers = getFibonacciMultipliers(maxValue)
    for (i in 1..t) {
        val target = readLine()!!.toULong()
        val multipliers = mutableListOf<WrapperULong>()
        fibonacciMultipliers.forEach {
            var res = 1UL
            var tmpTarget = target
            while (true) {
                res = res * it
                if (res <= target) {
                    if (tmpTarget % it != 0UL) break
                    multipliers.add(WrapperULong(it))
                    tmpTarget /= it
                } else break
            }
        }

        val result = getNumberWaysRepresentAsProductFibonacciNumbers(multipliers = multipliers, target = target)
        println(result)
    }
}

fun getNumberWaysRepresentAsProductFibonacciNumbers(
    minIndex: Int = 0,
    multipliers: List<WrapperULong>,
    target: ULong,
    prefix: MutableList<WrapperULong> = mutableListOf(),
    set: MutableSet<List<ULong>> = mutableSetOf(),
    set2: MutableSet<List<ULong>> = mutableSetOf(),
    product: ULong = 1UL
): Int {
    if (target % product != 0UL) return set.size
    if (product > target) return set.size
    val prefixMapped = prefix.map { it.value }
    if (set2.contains(prefixMapped)) return set.size else set2.add(prefixMapped)
    if (product == target) set.add(prefixMapped)
    for ((index, item) in multipliers.withIndex()) {
        if (index < minIndex) continue
        if (item !in prefix) {
            prefix.add(item)
            getNumberWaysRepresentAsProductFibonacciNumbers(
                index,
                multipliers,
                target,
                prefix,
                set,
                set2,
                product * item.value
            )
            prefix.removeAt(prefix.lastIndex)
        }
    }
    return set.size
}

fun getFibonacciMultipliers(maxValue: ULong): List<ULong> {
    val result = mutableListOf<ULong>()
    result.add(1UL)
    result.add(1UL)

    var i = 2
    while (true) {
        val newValue = result[i - 2] + result[i - 1]
        if (newValue > maxValue) break
        result.add(newValue)
        i++
    }
    return result.subList(2, result.size)
}

data class WrapperULong(val value: ULong) {
    private val hash = random.nextInt()

    override fun toString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return hash
    }

    override fun equals(other: Any?): Boolean {
        return this.hashCode() == (other as WrapperULong).hashCode()
    }

    companion object {
        val random = Random(System.currentTimeMillis())
    }
}


@RunWith(Parameterized::class)
class Test1(
    private val inputStringCount: String,
    private val inputStringItems: List<ULong>,
) {
    @Test
    fun test() {
        println("inputStringCount: $inputStringCount")
        println("inputStringItems: $inputStringItems")

        val t = inputStringCount.toInt()
        val maxValue = 10.0.pow(18).toULong()
        val fibonacciMultipliers = getFibonacciMultipliers(maxValue)
        println(fibonacciMultipliers)
        for (i in 1..t) {
            val target = inputStringItems[i - 1]
            val multipliers = mutableListOf<WrapperULong>()
            fibonacciMultipliers.forEach {
                var res = 1UL
                var tmpTarget = target
                while (true) {
                    res *= it
                    if (res <= target) {
                        if (tmpTarget % it != 0UL) break
                        multipliers.add(WrapperULong(it))
                        tmpTarget /= it
                    } else break
                }
            }

            val result = getNumberWaysRepresentAsProductFibonacciNumbers(multipliers = multipliers, target = target)
            println("target=${target} result=$result")
        }

    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
//                arrayOf("5", listOf(2UL, 7UL, 8UL, 40UL, 64UL)),
//                arrayOf("1", listOf(10.0.pow(18).toULong())),
//                arrayOf("8", listOf(2,3,4,5,6,7,8,9).map { it.toULong() }),
//                arrayOf("10", listOf(10,11,12,13,14,15,16,17,18,19).map { it.toULong() }),
//                arrayOf("10", listOf(20,21,22,23,24,25,26,27,28,29).map { it.toULong() }),
//                arrayOf("10", listOf(1120,1121,1122,1123,1124,1125,1126,1127,1128,1129).map { it.toULong() }),
//                arrayOf("4", listOf(160500643816367088, 259695496911122585, 420196140727489673, 679891637638612258).map { it.toULong() }),
//                arrayOf("1", listOf(2*3).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5*8).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5*8*13).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5*8*13*21).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5*8*13*21*34).map { it.toULong() }),
//                arrayOf("1", listOf(2*3*5*8*13*21*34*55).map { it.toULong() }),
//                arrayOf("1", listOf(2UL*3UL*5UL*8UL*13UL*21UL*34UL*55UL*89UL).map { it.toULong() }),
//                arrayOf("1", listOf(2UL*3UL*5UL*8UL*13UL*21UL*34UL*55UL*89UL*144UL).map { it.toULong() }),
//                arrayOf("1", listOf(2UL*3UL*5UL*8UL*13UL*21UL*34UL*55UL*89UL*144UL*233UL).map { it.toULong() }),
//                arrayOf("1", listOf(2UL*3UL*5UL*8UL*13UL*21UL*34UL*55UL*89UL*144UL*233UL*377UL)),
//                arrayOf("1", listOf(13UL*21UL*34UL*55UL*89UL*144UL*233UL*377UL)),
                arrayOf("1", listOf(2448UL)),

                )
        }
    }
}

