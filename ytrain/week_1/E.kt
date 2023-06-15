package ytrain

fun main() {
    val stringPalindrome = readLine()!!
    val result = breakPalindrome(stringPalindrome)
    println(result)
}

fun breakPalindrome(stringPalindrome: String): String {
    if (stringPalindrome.length <= 1) return ""
    val stringPalindromeBuilder = StringBuilder(stringPalindrome)
    for (i in 0 until stringPalindromeBuilder.length / 2) {
        if (stringPalindromeBuilder[i] != 'a') {
            stringPalindromeBuilder[i] = 'a'
            return stringPalindromeBuilder.toString()
        }
    }
    stringPalindromeBuilder[stringPalindromeBuilder.lastIndex] = 'b'
    return stringPalindromeBuilder.toString()
}