package toff.parsing_regexp.B

fun main() {
    var text = readLine()!!

    // fin
    text = Regex("([^a-z])((the\\s)|(the(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("([^a-z])(the)\$", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("^((the\\s)|(the(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "")
    text = Regex("^(the)$", RegexOption.IGNORE_CASE).replace(text, "")

    text = Regex("([^a-z])((an\\s)|(an(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("([^a-z])(an)\$", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("^((an\\s)|(an(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "")
    text = Regex("^(an)$", RegexOption.IGNORE_CASE).replace(text, "")

    text = Regex("([^a-z])((a\\s)|(a(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("([^a-z])(a)\$", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = Regex("^((a\\s)|(a(?=[^a-z])))", RegexOption.IGNORE_CASE).replace(text, "")
    text = Regex("^(a)$", RegexOption.IGNORE_CASE).replace(text, "")

    text = Regex(" {2,}").replace(text, " ")


    // 1 поколение
    text = Regex("c(?=[ie])").replace(text, "s") // +
    text = Regex("C(?=[ie])").replace(text, "S") // +
    text = Regex("ck").replace(text, "k") // +
    text = Regex("Ck").replace(text, "K") // +
    text = Regex("c").replace(text, "k") // +
    text = Regex("C").replace(text, "K") // +


    // 2 поколение
    var lastText = ""
    while (text != lastText) {
        lastText = text
        text = Regex("([a-z])\\1", RegexOption.IGNORE_CASE).replace(text){
            if (it.value[0] == 'E') return@replace "I"
            if (it.value[0] == 'e') return@replace "i"
            if (it.value[0] == 'O') return@replace "U"
            if (it.value[0] == 'o') return@replace "u"
            return@replace it.value[0].toString()
        }
    }
    // 3 поколение
    text += " "
    text = Regex("([a-z])e(?=[^a-z])", RegexOption.IGNORE_CASE).replace(text, "$1")
    text = text.trim()

    text = Regex(" {2,}").replace(text, " ")
    println(text)
}