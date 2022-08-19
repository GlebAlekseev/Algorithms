package cormen.введение.p1_1


fun main(){
    val arrayExample = arrayOf(31,41,59,26,41,58)
    val value = 26
    println(arrayExample.toList())
    println(linearSearch(arrayExample, value))
}

fun linearSearch(array: Array<Int>, value: Int): Int?{
    array.forEachIndexed { i, it ->
        if (it == value)
            return i
    }
    return null
}