package ytrain.week_2

fun main(){
    val text = readln().trim()
    var i = 0
    val map = mapOf<Int, HashSet<Int>>(
        0 to HashSet(),
        1 to HashSet(),
        2 to HashSet(),
        3 to HashSet(),
        4 to HashSet(),
        5 to HashSet(),
        6 to HashSet(),
        7 to HashSet(),
        8 to HashSet(),
        9 to HashSet(),
    )
    while (i < text.lastIndex){
        map[text[i+1].digitToInt()]!!.add(text[i].code)
        i += 2
    }
    val result = map.count { it.value.size == 3 }
    println(result)
}
