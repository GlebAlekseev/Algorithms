package ytrain.week_2


fun main() {
    val dictionary = readln().trim().split(" ")
    val trie = Trie()
    dictionary.forEach {
        trie.insert(it)
    }
    val words = readln().trim().split(" ")
    val list = mutableListOf<String>()
    words.forEach {
        val modifiedWord = trie.findOrReturn(it)
        list.add(modifiedWord)
    }
    println(list.joinToString(" "))
}

data class TrieNode(
        val char: Char? = null,
        val parent: TrieNode? = null,
        val children: MutableMap<Char, TrieNode> = mutableMapOf(),
        var isTermination: Boolean = false,
){
    fun getText(): String{
        var currentNode: TrieNode? = this
        val stringBuilder = StringBuilder()
        while (currentNode?.parent != null){
            stringBuilder.append(currentNode.char)
            currentNode = currentNode.parent
        }
        return stringBuilder.reversed().toString()
    }
}

class Trie {
    private val root: TrieNode = TrieNode()

    fun insert(word: String) {
        var currentNode = root
        for (char in word) {
            val child = currentNode.children[char]
            if (child != null) {
                currentNode = child
            } else {
                val newChild = TrieNode(parent = currentNode, char = char)
                currentNode.children[char] = newChild
                currentNode = newChild
            }
        }
        currentNode.isTermination = true
    }

    fun findOrReturn(word: String): String {
        var currentNode = root
        for (char in word) {
            val child = currentNode.children[char] ?: return word
            if (child.isTermination) {
                return child.getText()
            }
            currentNode = child
        }
        return word
    }
}