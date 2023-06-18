package ytrain.week_2

fun main(){
    val (_, k) = readLine()!!.trim().split(" ").map { it.toInt() }
    val measurements = readLine()!!.trim().split(" ").map { it.toInt() }
    val result = containsDuplicateAtDistanceOfUpToK(measurements, k)
    println(if (result) "YES" else "NO")
}

fun containsDuplicateAtDistanceOfUpToK(list: List<Int>, k: Int): Boolean {
    val hashSet = hashSetOf<Int>()
    for (i in list.indices){
        if (hashSet.size == k + 1) hashSet.remove(list[i - k - 1])
        if (hashSet.contains(list[i])) return true
        hashSet.add(list[i])
    }
    return false
}
