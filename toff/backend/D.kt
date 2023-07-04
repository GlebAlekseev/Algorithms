package toff_backend

import java.math.BigDecimal
import kotlin.math.absoluteValue


fun main() {
    val q = readln().toInt()
    for (i in 1..q) {
        val (l, r) = readln().split(" ").map { it.toInt() }
        if ((r - l).absoluteValue > 5) {
            println(9)
            continue
        }
        var acc = BigDecimal(1)
        for (value in l..r) {
            acc *= BigDecimal(value)
        }
        val result = sumDigitsInStringWhileBecomeLeast10(acc.toString())
        println(result)
    }
}


fun sumDigitsInStringWhileBecomeLeast10(value: String): Int {
    if (value.length == 1) return value.toInt()
    val result = value.fold(0) { acc: Int, c: Char ->
        acc + c.code - '0'.code
    }
    return sumDigitsInStringWhileBecomeLeast10(result.toString())
}