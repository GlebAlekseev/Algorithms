package cormen.введение.p1_2

fun main(){
    val arrayExample = arrayOf(31,41,59,26,41,58)
    val value = 26
    println(arrayExample.toList())
    println(linearSearch(arrayExample, value))
}

fun linearSearch(array: Array<Int>, value: Int): Int?{
    array.forEachIndexed { i, it ->                     // c1 n
        if (it == value)                                // c2 n
            return i                                    // c3 1
    }
    return null                                         // c4 1
}

// T(n) = c1n + c2n + c3*1 + c4*1 = n(c1+c2)+c3+c4
// T(n) = Cn+C
// Линейно