package toff.parsing_regexp.C

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    scanner.forEach {
        val text = it!!
        val regex = Regex("\\\\circle\\{\\((\\d+),(\\d+)\\)")
        val result = regex.replace(text, "\\\\circle{($2,$1)")
        println(result)
    }
}