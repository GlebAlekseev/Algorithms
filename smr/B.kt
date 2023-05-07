package smr.B

fun main() {
    val n = readLine()!!.toInt()
    val activeUidSet = HashSet<Int>()
    var sessionCounter = 0
    var crashCounter = 0
    val launch = "APPLICATION_FINISHED_LAUNCHING".hashCode()
    val finish = "APPLICATION_TERMINATED".hashCode()
    for (i in 1..n) {
        val name = readLine()!!.hashCode()
        val uid = readLine()!!.toInt()
        when (name) {
            finish -> {
                if (uid in activeUidSet){
                    activeUidSet.remove(uid)
                    sessionCounter++
                }else{
                    crashCounter++
                    sessionCounter++
                }
            }
            else -> {
                if (name == launch){
                    if (uid in activeUidSet) {
                        sessionCounter++
                        crashCounter++
                    } else {
                        activeUidSet.add(uid)
                    }
                }else {
                    if (uid !in activeUidSet){
                        activeUidSet.add(uid)
                        sessionCounter++
                        crashCounter++
                    }
                }
            }
        }
    }

    val result = ((crashCounter + activeUidSet.size) * 100) / ((sessionCounter + activeUidSet.size).toDouble())
    if (result == 0.0) {
        println(0)
    } else {
        println(result.roundToString(6).toDouble())
    }
}

fun Double.roundToString(n: Int) = "%.${n}f"
    .format(this)
    .replace(Regex("\\,0+$"), ",0")
    .replace(",", ".")