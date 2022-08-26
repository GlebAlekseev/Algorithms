package cormen.c2_введение.p1_3

// Бинарный поиск
// Поиск по отсортированному массиву

fun main(){
    val arrayExample = arrayOf(1,2,3,4,5,6,7,8,9,10)
    println(binarySearch(arrayExample,0))

}

fun binarySearch(array: Array<Int>,target: Int): Int?{
    // null или index
    return binarySearch(array,target,0,array.size-1)
}


fun binarySearch(array: Array<Int>, target: Int,minIndex: Int, maxIndex: Int): Int?{
    if (minIndex == maxIndex){                                              // c1 1
        return if (array[minIndex] == target) minIndex else null            // c2 1
    }
    val mid = (minIndex+maxIndex)/2                                         // c3 1
    if (array[mid] >= target){                                              // c4 1
        return binarySearch(array,target,minIndex,mid)
    }else{
        return binarySearch(array,target,mid+1,maxIndex)
    }
}


// с1-с4 выполняется за константное время O(1), поэтому сложность O(1 * log(n)) = O(log(n))
// Рекурсия = log(n)
