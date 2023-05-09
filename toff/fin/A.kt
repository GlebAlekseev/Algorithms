package toff.fin

import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        var text = line
        text = Regex("\\\\texttt\\{([a-zA-Z]+|[0-9]+)}").replace(text, "\\\\begin{bfseries}$1\\\\end{bfseries}")
        println(text)
    }
}