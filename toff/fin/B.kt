package toff.fin

fun main(){
    val n = readLine()!!.toInt()
    val stack = java.util.Stack<Int>()
    val hashSet = HashSet<Int>()
    for (i in 1..n){
        val x = readLine()!!.toInt()
        // O(1)
        when {
            x > 0 -> {
                stack.push(x)
                hashSet.add(x)
            }
            x < 0 ->{
                val find = hashSet.contains(-x)
                if (find){
                    hashSet.remove(-x)
                    if (stack.isNotEmpty()){
                        if (stack.peek() == -x) stack.pop()
                    }
                    while (stack.isNotEmpty() && stack.peek() !in hashSet){
                        stack.pop()
                    }
                }
            }
            x == 0 ->{
                if (stack.isNotEmpty()){
                    val removed = stack.pop()
                    removed?.let { hashSet.remove(it) }
                    while (stack.isNotEmpty() && stack.peek() !in hashSet){
                        stack.pop()
                    }
                }
            }
        }
        if (stack.isEmpty()) println(0) else println(stack.peek())
    }
}
