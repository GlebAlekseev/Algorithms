package toff.search_trees.B

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

fun main() {
    readLine()
    val requestList = readLine()!!.split(" ").map { it.toInt() }
    val twoThreeTree = TwoThreeTree()
    requestList.forEach { request ->
        twoThreeTree.add(request)
    }
    twoThreeTree.printTree()
}

// Левостороннее B+ дерево
class TwoThreeTree() {
    var root: Node? = null

    // поиск листа, в который вставлять
    private fun findLeaf(root: Node, target: Int): Node {
        var currentNode = root
        while (!currentNode.isLeaf) {
            for (i in 0..currentNode.valueList.size) {
                if (i == currentNode.valueList.size || target <= currentNode.valueList[i]) {
                    currentNode = currentNode.childList[i]
                    break
                }
            }
        }
        return currentNode
    }

    fun add(newValue: Int) {
        fun initRoot(node: Node?): Node {
            node?.let { return node }
            return Node().also { root = it }
        }

        val leaf = findLeaf(initRoot(root), newValue)
        // Позиция для нового ключа
        var pos = 0
        leaf.valueList.forEach {
            if (newValue < it) return@forEach
            pos++
        }
        // Вставка ключа
        leaf.valueList.add(pos, newValue)
        if (leaf.isOverflow) {
            split(leaf)
        }
    }

    private fun split(node: Node) {
        val newNode = Node()
        val middleValue: Int
        if (node.isLeaf) {
            newNode.valueList.addFirst(node.valueList.removeLast())
            newNode.valueList.addFirst(node.valueList.removeLast())
            middleValue = node.valueList.removeLast()
            node.valueList.add(middleValue)
        } else {
            newNode.valueList.addFirst(node.valueList.removeLast())
            middleValue = node.valueList.removeLast()

            newNode.childList.addFirst(node.childList.removeLast().apply { parent = newNode })
            newNode.childList.addFirst(node.childList.removeLast().apply { parent = newNode })
        }

        if (node == root) {
            root = Node().apply {
                valueList.add(middleValue)
                childList.add(node)
                childList.add(newNode)
            }
            node.parent = root
            newNode.parent = root
        } else {
            newNode.parent = node.parent
            val parent = node.parent!!

            // Позиция middleValue в отце
            var pos = 0
            parent.valueList.forEach {
                if (middleValue < it) return@forEach
                pos++
            }

            // Добавить middleValue в отца + добавить newNode отцу в childList
            parent.valueList.add(pos, middleValue)
            parent.childList.add(pos + 1, newNode)

            if (parent.isOverflow) {
                split(parent)
            }
        }
    }

    fun printTree(node: Node? = root, lvl: Int = 65) {
        node ?: return
        if (node.isLeaf) {
            print(node.valueList.joinToString(" ${lvl.toChar()} "))
        } else {
            node.childList.forEachIndexed { index, item ->
                printTree(item, lvl + 1)
                if (index != node.childList.size - 1) {
                    print(" ${lvl.toChar()} ")
                }
            }
        }
    }

    fun printTreeTest(node: Node? = root, lvl: Int = 65, stringBuilder: StringBuilder, stringBuilder2: StringBuilder) {
        node ?: return
        if (node.isLeaf) {
            stringBuilder.append(node.valueList.joinToString(" ${lvl.toChar()} "))
            stringBuilder2.append(node.valueList.joinToString(""))
        } else {
            node.childList.forEachIndexed { index, item ->
                printTreeTest(item, lvl + 1, stringBuilder, stringBuilder2)
                if (index != node.childList.size - 1) {
                    stringBuilder.append(" ${lvl.toChar()} ")
                }
            }
        }
    }

    data class Node(
        val valueList: LinkedList<Int> = LinkedList(),
        val childList: LinkedList<Node> = LinkedList(),
        var parent: Node? = null
    ) {
        val isLeaf: Boolean
            get() = childList.isEmpty()
        val isOverflow: Boolean
            get() = (valueList.size == 4 && isLeaf) || (!isLeaf && valueList.size == 3)
    }
}


@RunWith(Parameterized::class)
class Test2(
    private val inputString: String,
    private val expected: String,
) {
    @Test
    fun test() {
        println("inputString=${inputString}")
        val requestList = inputString.split(" ").map { it.toInt() }
        val twoThreeTree = TwoThreeTree()
        requestList.forEach { request ->
            println(request)
            twoThreeTree.add(request)
        }
        val actualStringBuilder = StringBuilder()
        val actualStringBuilder2 = StringBuilder()
        twoThreeTree.printTreeTest(stringBuilder = actualStringBuilder, stringBuilder2 = actualStringBuilder2)

        Assert.assertEquals(actualStringBuilder.toString(), expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("1 2 3 4 5 6 7 8 9 10", "1 C 2 B 3 C 4 A 5 C 6 B 7 C 8 B 9 C 10"),
                arrayOf("1 2", "1 A 2"),
                arrayOf("1 2 3", "1 A 2 A 3"),
                arrayOf("1 2 4 5 3", "1 B 2 A 3 B 4 B 5"),
                arrayOf("1", "1"),
                arrayOf("1 2", "1 A 2"),
                arrayOf("2 1", "1 A 2"),
                arrayOf("1 2 3", "1 A 2 A 3"),
                arrayOf("3 2 1", "1 A 2 A 3"),
                arrayOf("2 3 1", "1 A 2 A 3"),
                arrayOf("5 4 3 2 1", "1 B 2 B 3 A 4 B 5"),
                arrayOf("1 2 3 6 5 4", "1 B 2 A 3 B 4 A 5 B 6"),
                arrayOf("1 10 2 9 3 8 4 7 5 6", "1 C 2 B 3 C 4 A 5 C 6 B 7 C 8 B 9 C 10"),
                arrayOf("8 5 13 3 9 2 12 11 10 1 4 6 7", "1 C 2 B 3 C 4 C 5 A 6 C 7 B 8 C 9 B 10 C 11 B 12 C 13"),
                arrayOf("6 1 5 2 4 3", "1 B 2 A 3 B 4 A 5 B 6"),
                arrayOf("15 14 13 12 11 10 9 8 7 6 5 4 3 2 1", "22222"),
                arrayOf("10 9 8 7 6 5 4 3 2 1", "1 C 2 B 3 C 4 A 5 C 6 B 7 C 8 B 9 C 10"),
                arrayOf("3 1 2 3 2", "1 B 2 B 2 A 3 B 3"),
                arrayOf("3 7 3 1 5 7 10 7 2 4 10", "1 C 2 C 3 B 3 C 4 C 5 A 7 C 7 B 7 C 10 C 10"),

                )
        }
    }
}


class Test1 {
    @Test
    fun test() {
//        (65..80).forEach {
//            println("=${it.toChar()}")
//        }
        val twoThreeTree = TwoThreeTree()
        twoThreeTree.add(1)
        twoThreeTree.add(10)
        twoThreeTree.add(2)
        twoThreeTree.add(9)
        twoThreeTree.add(3)
        twoThreeTree.add(8)
        twoThreeTree.add(4)
//        twoThreeTree.add(7)
//        twoThreeTree.add(5)
//        twoThreeTree.add(6)
        twoThreeTree.printTree()
//        for (i in 1..100){
//            val list = (1..i).toList()
//            println(list.joinToString(""))
//            repeat(100*i){
//                val twoThreeTree = TwoThreeTree()
//                val newList = list.shuffled()
//            println(newList)
//                newList.forEach { twoThreeTree.add(it) }
//                val actualStringBuilder = StringBuilder()
//                val actualStringBuilder2 = StringBuilder()
//                twoThreeTree.printTreeTest(stringBuilder = actualStringBuilder, stringBuilder2 = actualStringBuilder2)
//                if (actualStringBuilder2.toString() != list.joinToString("")) throw RuntimeException("newList=${newList.joinToString(" ")} is bad\n\t" +
//                        "actualStringBuilder2=$actualStringBuilder2") else println("good i=$it")
//            }
//        }

        //8 5 13 3 9 2 12 11 10 1 4 6 7
//        val twoThreeTree = TwoThreeTree()
//        val list = (1..10).toList().shuffled()

//


    }

//    @Test
//    fun test2() {
//        val twoThreeTree = TwoThreeTree()
//        twoThreeTree.add(8)
//        twoThreeTree.add(5)
//        twoThreeTree.add(13)
//        twoThreeTree.add(3)
//        twoThreeTree.add(9) //
//        twoThreeTree.add(2)
//        twoThreeTree.add(12)
//        twoThreeTree.add(11)
//        twoThreeTree.add(10)
//        twoThreeTree.add(1)
////        twoThreeTree.add(4)
////        twoThreeTree.add(6) // +
////        twoThreeTree.add(7)
//        twoThreeTree.printTree()
//        // 8 5 13 3 9 2 12 11 10 1 4 6 7
//        // 1 C 2 B 3 C 4 C 5 B 8 C 9 A 6 C 7 B 10 C 11 B 12 C 13
//
//    }
}
