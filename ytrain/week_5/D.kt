package ytrain.week_5

fun main() {
    val s = readln()
    var left = 0
    var right = s.length - 1

    while (left < right && s[left] == s[right]) {
        val nowChar = s[left]

        while (left < right && s[left] == nowChar) {
            left++
        }

        while (right >= left && s[right] == nowChar) {
            right--
        }
    }

    println(right - left + 1)
}
