package toff.binary_search.C

fun main() {
    val n: Int = readLine()!!.toInt()
    val result = binaryGuessing(r = n - 1)
    printlnWithoutBuffer("! $result")
}

fun binaryGuessing(l: Int = 0, r: Int): Int {
    if (l == r) return l + 1
    if (r - l == 1) {
        printlnWithoutBuffer(r + 1)
        return if (readLine() == ">=") r + 1 else l + 1
    }

    val middle = (l + r) / 2
    printlnWithoutBuffer(middle + 1)
    return when (readLine()) { // target < middle <= target
        "<" -> binaryGuessing(l, r = middle - 1)
        ">=" -> binaryGuessing(middle, r)
        else -> throw Exception()
    }
}

fun printlnWithoutBuffer(arg: Any) {
    println(arg)
    System.out.flush()
}