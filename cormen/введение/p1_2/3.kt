package cormen.введение.p1_2

import cormen.введение.theory.mergeSort

fun main(){
    val arrayX = arrayOf(1,2,3,4,5,6,6,7,8,9)
    // Определить два одинаковых числа в последовательности arrayX за n*log(n)
    // Сортировать за nlog(n) и проверять одинаковые числа
    // Если сортировка in place, то нижняя граница будет за O(2), верхняя nlog(n)
    // Иначе всегда nlog(n)
    mergeSort(arrayX,0,arrayX.size-1)
    for (i in 1..arrayX.size){
        if (arrayX[i-1] == arrayX[i]) {
            println("Дубликат имеет число ${arrayX[i]}")
            break
        }
    }
}
