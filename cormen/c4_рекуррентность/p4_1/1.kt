package cormen.c4_рекуррентность.p4_1

fun main(){
    val arrayExample1 = arrayOf(0,13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15-4,7)
    val arrayExample2 = arrayOf(-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-11)

//    println(findMaximumSubArray(arrayExample1,0,arrayExample1.size-1))
    println(findMaximumSubArray(arrayExample2,0,arrayExample2.size-1))
}


// Точкой выхода из рекурсии является диапазон из одного элемента high == low
// Диапазон делится по полам до тех пор пока не останется один элемент
// Каждый раз получаю три диапазона и для каждого их сумма
// Выбираю из них максимальный и возвращаю
// Таким образом, будет выведен диапазон с максимальной суммой для первоначального диапазона
// O(nlog(n))

fun findMaximumSubArray(array: Array<Int>, low: Int, high: Int): Triple<Int, Int, Int>{
    if (high == low)
        return Triple(low, high, array[low])
    else{
        var mid = (low+high)/2
        val(leftLow, leftHigh, leftSum) = findMaximumSubArray(array, low, mid)
        val(rightLow, rightHigh, rightSum) = findMaximumSubArray(array,mid + 1,high)
        val(crossLow, crossHigh, crossSum) = findMaxCrossingSubArray(array, low, mid, high)

        return if (leftSum >= rightSum && leftSum >= crossSum)
            Triple(leftLow, leftHigh, leftSum)
        else if(rightSum >= leftSum && rightSum >= crossSum)
            Triple(rightLow, rightHigh, rightSum)
        else
            Triple(crossLow, crossHigh, crossSum)
    }

}



// Данная функция получает на вход исходный массив и индексы
// Функция из центра идет влево и ищет максимальную сумму, сохраняет индекс
// Аналогично в другую сторону
// Складывает сумму по обе стороны и возвращает вместе с индексами
// O(n) - low..high

fun findMaxCrossingSubArray(array: Array<Int>, low: Int, mid: Int, high: Int): Triple<Int, Int, Int>{
    var leftSum = Int.MIN_VALUE
    var sum = 0
    var maxLeft = 0
    for (i in mid downTo low){
        sum +=array[i]
        if (sum > leftSum){
            leftSum = sum
            maxLeft = i
        }
    }
    var rightSum = Int.MIN_VALUE
    sum = 0
    var maxRight = 0
    for (j in mid+1..high){
        sum +=array[j]
        if (sum > rightSum){
            rightSum = sum
            maxRight = j
        }
    }
    return Triple(maxLeft,maxRight,leftSum+rightSum)
}