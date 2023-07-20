package ytrain.week_5

fun main() {
    val secret = readln()
    val guess = readln()
    var bulls = 0
    var cows = 0
    val inSecret = mutableMapOf<Char, Int>()
    val inGuess = mutableMapOf<Char, Int>()

    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            bulls++
        } else {
            inSecret[secret[i]] = inSecret.getOrDefault(secret[i], 0) + 1
            inGuess[guess[i]] = inGuess.getOrDefault(guess[i], 0) + 1
        }
    }

    for (digit in inSecret.keys) {
        if (inGuess.containsKey(digit)) {
            cows += minOf(inSecret[digit]!!, inGuess[digit]!!)
        }
    }

    println(bulls)
    println(cows)
}
