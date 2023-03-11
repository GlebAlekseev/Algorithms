package toff.sorting.counting_sort

data class Student(
    val name: String,
    val group: Int
)

val studentList = listOf(
    Student("a", 1),
    Student("b", 1),
    Student("c", 2),
    Student("d", 2),
    Student("e", 2),
    Student("f", 5),
    Student("g", 1),
)

fun main() {
    val res = countingSort(studentList) { it.group }
    println(res)
}

// stable counting sort
inline fun <reified T> countingSort(collection: Collection<T>, compare: (a: T) -> Int): Collection<T> {
    val max = collection.maxOf { compare(it) }
    val min = collection.minOf { compare(it) }
    val countArray = IntArray(max - min + 1)
    val result = arrayOfNulls<T>(collection.size)
    for (i in collection.indices) {
        countArray[compare(collection.elementAt(i)) - min]++
    }
    for (i in 1 until countArray.size) {
        countArray[i] += countArray[i - 1]
    }
    for (i in collection.size - 1 downTo 0 step 1) {
        val index = compare(collection.elementAt(i)) - min
        result[countArray[index] - 1] = collection.elementAt(i)
        countArray[index]--
    }
    return result.map { it!! }
}