package toff.parsing_regexp.D

import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        var text = line
        text = Regex("\\\$v_(\\w)\\\$").replace(text, "v[$1]")
        text = Regex("\\\$v_\\{(\\w+)}\\\$").replace(text, "v[$1]")
        println(text)
    }
}