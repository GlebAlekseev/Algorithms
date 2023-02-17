package toff.sorting

fun main(){
    readLine()
    val line = readLine()!!
    val result = getMaxPalindrome(line){ char ->
        char.code
    }
    println(result)
}

fun getMaxPalindrome(line: String, compare: (a: Char) -> Int): String {
    val max = line.maxOf { compare(it) }
    val min = line.minOf { compare(it) }
    val countArray = IntArray(max - min + 1)
    val result = StringBuilder()
    for (i in line.indices) {
        countArray[compare(line.elementAt(i)) - min]++
    }
    var maxIndex = -1
    var maxValue = -1
    for (i in countArray.indices){
        val value = countArray[i]
        if (value % 2 == 1){
            if (value > maxValue){
                maxValue = value
                maxIndex = i
            }
        }
    }
    for (i in countArray.indices){
        if (i == maxIndex) {
            countArray[i] -= 1
            countArray[i] /=2
        }
        else countArray[i] /= 2
    }
    countArray.forEachIndexed { index, count ->
        val symbol = (index + min).toChar()
        for (i in 1..count){
            result.append(symbol)
        }
    }
    if (maxIndex != -1) result.append((maxIndex + min).toChar())
    for (index in countArray.size - 1 downTo 0 step 1){
        val symbol = (index + min).toChar()
        for (i in 1..countArray[index]){
            result.append(symbol)
        }
    }
    return result.toString()
}