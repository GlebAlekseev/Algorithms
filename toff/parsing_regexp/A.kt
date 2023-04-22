package toff.parsing_regexp.A

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    scanner.forEach {
        val text = it!!
        val regex = Regex("a+b{2,}c+")
        val result = regex.replace(text, "QQQ")
        println(result)
    }
}