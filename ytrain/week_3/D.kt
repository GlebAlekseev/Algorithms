package ytrain.week_3

fun main() {
    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val coordinates = readln().trim().split(" ").map { it.toInt() }

    var l = 0
    var r = coordinates.last()
    fun check(target: Int): Boolean {
        var count = 1
        var lastCoordinate = coordinates.first()
        for (coordinate in coordinates){
            if (coordinate - lastCoordinate >= target){
                count++
                lastCoordinate = coordinate
            }
        }
        return count >= k
    }
    while (r > l) {
        val middle = (l + r + 1) / 2

        if (check(middle)) {
            l = middle
        } else {
            r = middle - 1
        }
    }
    println(l)
}