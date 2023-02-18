package toff.sorting.E2

fun main() {
    val n = readLine()!!.toInt()
    val array = IntArray(n)
    val stepArray = readLine()!!.split(" ").map { it.toInt() }

    var counter = 1
    var maxIndexZero = n
    print(counter)
    stepArray.forEach { index ->
        array[index - 1] = 1
        if (index < maxIndexZero) {
            counter++
        }else {
            for (j in maxIndexZero - 2 downTo 0 step 1){
                if (array[j] == 0) {
                    maxIndexZero = j + 1
                    break
                }
                counter--
            }
        }
        print(" $counter")
    }
    println()
}

// Задача E. Что? Да! Пузырек

fun countBubbleExternalCycles(array: IntArray): Int {
    var counter = 0
    var isGood = false
    for (i in array.size - 1 downTo 0 step 1) {
        if (!isGood && array[i] == 0) {
            isGood = true
        }
        if (isGood && array[i] == 1) {
            counter++
        }
    }
    return counter + 1
}

fun bubbleSort(array: IntArray): Int {
    var counter = 0
    for (j in 0 until array.size) {
        var hadSwaps = false
        for (i in 0 until array.size - 1) {
            if (array[i] > array[i + 1]) {
                array[i] = array[i + 1].also { array[i + 1] = array[i] }
                hadSwaps = true
            }
        }
        counter++
        if (!hadSwaps) break
    }
    return counter
}