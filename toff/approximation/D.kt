import Action.*
import kotlin.math.pow

//                         2, 3, 4, 5, 6, 7, 8, 9, 0, A
val matrixBasicStrategy = mapOf<String, List<Action>>(

    "<8" to         listOf(H, H, H, H, H, H, H, H, H, H),
    "9" to          listOf(H, H, H, H, D, H, H, H, H, H),
    "10" to         listOf(D, D, D, D, D, D, D, H, H, H),
    "11" to         listOf(D, D, D, D, D, D, D, H, H, H),
    "12" to         listOf(H, H, H, S, S, H, H, H, H, H),
    "13" to         listOf(H, S, S, S, S, H, H, H, H, H),
    "14" to         listOf(H, S, S, S, S, H, H, H, H, H),
    "15" to         listOf(S, S, S, S, S, H, H, H, H, H),
    "16" to         listOf(S, S, S, S, S, H, H, H, H, H),
    "17+" to        listOf(S, S, S, S, S, S, S, S, S, S),

    "A-8+" to       listOf(S, S, S, S, S, S, S, S, S, S),
    "A-7" to        listOf(S, S, S, D, D, S, S, H, H, H),
    "A-6" to        listOf(H, H, H, D, D, H, H, H, H, H),
    "A-5" to        listOf(H, H, H, H, D, H, H, H, H, H),
    "A-4" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "A-3" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "A-2" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "A-A" to        listOf(S, S, S, S, S, S, S, S, S, H),

    "10-10" to      listOf(S, S, S, S, S, S, S, S, S, S),
    "9-9" to        listOf(S, S, S, S, S, S, S, S, S, S),
    "8-8" to        listOf(S, S, S, S, S, S, S, S, H, H),
    "7-7" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "6-6" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "5-5" to        listOf(D, D, D, D, D, D, D, H, H, H),
    "4-4" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "3-3" to        listOf(H, H, H, H, H, H, H, H, H, H),
    "2-2" to        listOf(H, H, H, H, H, H, H, H, H, H),
)

const val RATE_STEP = 0.1 // 10% от банка
const val START_SUM = 500000 // 500к начальный банк
const val TARGET_SUM = 4200000 // 4.2кк целевой баланс

fun main() {
    // 52*6 = 312 карт
    // 2 3 4 5 6 7 8 9 10 J(10) Q(10) K(10) A(1|11) } 13
    var hiloIndex = 0
    // hiloIndex > 30 - большие карты преобладают
    // hiloIndex < -30 - маленькие карты преобладают
    // Базовая ставка 10% от банка
    // Когда преобладают большие, то 10%, иначе квадратично меньше...
    var sum = START_SUM // start balance

    val states = mutableListOf<String>()

    var rateSum = 0

    var roundNumber = 1
    var lastBetRate = 0
    while (true) {
        val command = readLine()!!
        states.add(command + "\t$roundNumber $sum $lastBetRate $hiloIndex $rateSum")
        when (command) {
            "shuffle" -> {
                hiloIndex = 0
            }
            "bet" -> {
//                                 372 373(err)

                if (sum >= TARGET_SUM || sum == 0) {
                    printlnWithoutBuffer("-1")
//                    if (roundNumber == 372) throw RuntimeException(
//                        "-1 round bet sum=$sum lastBetRate=$lastBetRate hiloIndex=$hiloIndex\n\n\n\tsates\nstates=${
//                            states.joinToString(
//                                "\n"
//                            )
//                        }"
//                    )
                    break
                } else {
                    val rate = getRateForHilo(hiloIndex, (sum * RATE_STEP).toInt())
                    lastBetRate = rate
                    rateSum+=rate
                    states.add(command +" " + rate)
                    if (roundNumber == 50){
                        printlnWithoutBuffer(5000000000)
                    }else{
                        printlnWithoutBuffer(rate)
                    }
                    if (roundNumber == 50) throw RuntimeException(
                        "rate=$rate round bet sum=$sum lastBetRate=$lastBetRate hiloIndex=$hiloIndex\n\n\n\tsates\nstates=${
                            states.joinToString(
                                "\n"
                            )
                        }"
                    )
                }
                if (roundNumber == 500) throw RuntimeException(
                    "round bet sum=$sum lastBetRate=$lastBetRate hiloIndex=$hiloIndex\n\n\n\tsates\nstates=${
                        states.joinToString(
                            "\n"
                        )
                    }"
                )
                //                 372 373(err)
            }
            else -> {
                val (dealerCards, playerCards) = command.split("#")
                val dealerScore = getScoreFromCards(dealerCards)
                val playerScore = getScoreFromCards(playerCards)
//                states.add(command + "\t$dealerScore # $playerScore")
                if (dealerCards.length == 1) {
                    val dealerIndexInMatrix = getCost(dealerCards[0]) - 2
                    var playerKeyInMatrix: String? = null
                    if (playerCards.length == 2){
                        if (getCost(playerCards[0]) == 11) {
                            playerKeyInMatrix = when(playerCards[1]){
                                'A' -> "A-A"
                                '2' -> "A-2"
                                '3' -> "A-3"
                                '4' -> "A-4"
                                '5' -> "A-5"
                                '6' -> "A-6"
                                '7' -> "A-7"
                                else -> "A-8+"
                            }
                        }
                        if (getCost(playerCards[0]) == getCost(playerCards[1]) && getCost(playerCards[0]) != 11){
                            playerKeyInMatrix = "${getCost(playerCards[0])}-${getCost(playerCards[1])}"
                        }
                    }
                    if (playerKeyInMatrix == null){
                        playerKeyInMatrix = when {
                            playerScore <= 8 -> "<8"
                            playerScore >= 17 -> "17+"
                            else -> playerScore.toString()
                        }
                    }
                    when(matrixBasicStrategy[playerKeyInMatrix]!![dealerIndexInMatrix]){
                        H -> printlnWithoutBuffer("hit")
                        S -> printlnWithoutBuffer("break")
                        D -> {
                            if (playerCards.length == 2){
                                lastBetRate *= 2
                                printlnWithoutBuffer("double")
                            }else {
                                printlnWithoutBuffer("hit")
                            }

                        }
                    }

//                    val targetScore = 21 - playerScore
//                    when {
//                        targetScore < 2 || targetScore < 4 && hiloIndex > 30 ->{
//                            printlnWithoutBuffer("break")
//                        }
//                        targetScore in 9..11 && hiloIndex > 30 || targetScore in 6..9 && hiloIndex < -30 -> {
//                            printlnWithoutBuffer("double")
//                        }
//                        else ->{
//                            printlnWithoutBuffer("hit")
//                        }
//                    }


                } else {
                    roundNumber++
                    // Подведение результата
                    when {
//                        playerScore > 21 && dealerScore > 21 -> sum -= lastBetRate
                        playerScore > 21 -> sum -= lastBetRate
                        dealerScore == playerScore -> {}
                        dealerScore > 21 -> sum += lastBetRate
                        dealerScore < playerScore -> sum += lastBetRate
                        dealerScore > playerScore && dealerScore <=21 -> sum -= lastBetRate
                        else -> {}
                    }
                    (dealerCards + playerCards).forEach {
                        when (it) {
                            '2', '3', '4', '5', '6' -> {
                                hiloIndex += 1
                            }
                            '0', 'J', 'Q', 'K', 'A' -> {
                                hiloIndex -= 1
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

}

fun getScoreFromCards(text: String): Int {
    var score = 0
    text.forEach {
        score += getCost(it)
    }
    val countA = text.count { it == 'A' }
    return if (score <= 21) score else score - 10 * countA
}

fun getCost(char: Char): Int {
    return when (char) {
        '2' -> 2
        '3' -> 3
        '4' -> 4
        '5' -> 5
        '6' -> 6
        '7' -> 7
        '8' -> 8
        '9' -> 9
        '0' -> 10
        'J' -> 10
        'Q' -> 10
        'K' -> 10
        'A' -> 11
        else -> throw RuntimeException()
    }
}

fun printlnWithoutBuffer(arg: Any) {
    println(arg)
    System.out.flush()
}


// hiloIndex [-120;120]
// 0.25*base, при hiloIndex = 0
fun getRateForHilo(hiloIndex: Int, baseRate: Int): Int {
    // cast [-120;120] to [0;1]
    val hiloRatio = (hiloIndex + 30).toDouble() / 60
    val resultRatio = 20 * hiloRatio.pow(7).coerceIn(0.0, 1.0)
    val result = (baseRate * resultRatio).toInt()
    return if (result == 0) 1 else result
}


// Базовая стратегия =ВВВВ

enum class Action {
    H, S, D
}

