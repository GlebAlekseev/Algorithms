package ytrain.week_4



fun main(){
    val targetName = readln()
    val lineCount = readln().toInt()
    var tmpPath = mutableListOf<String>()
    val fullPaths = mutableMapOf<String, String>()
    repeat(lineCount){
        val line = readln()
        val name = line.trim()
        val spaces = line.count { it == ' ' }
        tmpPath = tmpPath.take(spaces).toMutableList()
        tmpPath.add(name)
        if (!fullPaths.containsKey(name)){
            fullPaths[name] = "/" + tmpPath.joinToString("/")
        }
    }
    println(fullPaths[targetName])
}