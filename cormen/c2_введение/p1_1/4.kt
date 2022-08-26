package cormen.c2_введение.p1_1

fun main(){
    val aBinaryDigitArray = arrayOf(1,0,1,0, 1,0,1,1)
    val bBinaryDigitArray = arrayOf(1,1,1,0, 1,0,0,1)
    val cBinaryDigitArray = binarySum(aBinaryDigitArray,bBinaryDigitArray)
    println("${aBinaryDigitArray.toList()}  \n${bBinaryDigitArray.toList()} \n= \n${cBinaryDigitArray.toList()}")
}

fun binarySum(a: Array<Int>, b: Array<Int>): Array<Int>{
    val result = Array(a.size+1){0}
    var save = 0
    a.forEachIndexed{ index, item ->
        var sum = (item + b[index] + save)
        save = if (sum >=2) 1 else 0
        result[index] = sum%2
    }
    result[a.size] = save
    return result
}