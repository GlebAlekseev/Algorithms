package toff.trie_aho.A

fun main() {
    val s = readLine()!!
    val t = readLine()!!
    val kmpList = kmp(t)
    var isFirst = true
    var j = 0
    for (i in s.indices){
        while (j == t.length || j > 0 && t[j] != s[i]){
            j = kmpList[j - 1]
        }
        if (s[i] == t[j]) j++
        if (j == t.length) if (isFirst) print(i - j + 1).also { isFirst = false } else print(" ${i - j + 1}")
    }
}

fun kmp(text: String): List<Int> {
    val result = MutableList(text.length) { 0 }
    for (i in 1 until result.size) {
        var j = result[i - 1]
        while (j > 0 && text[i] != text[j]) {
            j = result[j - 1]
        }
        if (text[i] == text[j]) j++
        result[i] = j
    }
    return result
}