package cormen.c12_binary_tree_search

import cormen.c10_базовые_структуры.theory.Stack
import cormen.c12_binary_tree_search.BinaryTreeSearch.Companion.Node


fun main(){
    val binaryTreeSearch = BinaryTreeSearch<Int>()
    binaryTreeSearch.forEach {
        println(it)
    }

}

class BinaryTreeSearch <T: Comparable<T>>: Iterable<Node<T>>{
    private var root: Node<T>? = null
    var traversalType = TraversalType.INORDER


    fun find(key: T,node: Node<T>? = root): Node<T>?{
        if (node == null || key == node.key) return node
        return if (key > node.key){
            find(key, node.right)
        }else{
            find(key, node.left)
        }
    }

    fun insert(key: T,node: Node<T>? = root) {
        if (node == null) root = Node(key = key)
        else{
            if (key == node.key){
                throw RuntimeException("Элемент с таким значением уже существует")
            }else if(key > node.key){
                if (node.right == null) {
                    node.right = Node(key = key)
                }else{
                    insert(key, node.right)
                }
            }else {
                if (node.left == null) {
                    node.left = Node(key = key)
                }else{
                    insert(key, node.left)
                }
            }
        }
    }

    fun remove(node: Node<T>? = root, key: T){
        TODO()
    }


    fun minimum(): Node<T>?{
        var lastNode: Node<T>? = null
        root?.leftForeach { node, _ ->
            lastNode = node
        }
        return lastNode
    }

    fun maximum(): Node<T>?{
        var lastNode: Node<T>? = null
        root?.rightForeach { node, _ ->
            lastNode = node
        }
        return lastNode
    }



    override fun iterator(): Iterator<Node<T>> = object : Iterator<Node<T>>{
        private val stack = Stack<Node<T>>()
        private val type = traversalType
        init {
            root?.let {
                stack.push(it)
            }
        }
        override fun hasNext(): Boolean = stack.isEmpty()

        override fun next(): Node<T> {
            return  when(type){
                TraversalType.INORDER -> inorderTraversal()
                TraversalType.PREORDER -> preorderTraversal()
                TraversalType.POSTORDER -> postorderTraversal()
            }
        }

        fun preorderTraversal(): Node<T>{
            // 1. Получаю последний элемент из стека
            val currentElement =  stack.pop()
            // 2. Добавляю правый элемент
            currentElement.right?.let {
                stack.push(it)
            }
            // 3. Добавляю левый элемент
            currentElement.left?.let {
                stack.push(it)
            }
            // 4. Возвращаю текущий элемент
            return currentElement
        }

        fun postorderTraversal(): Node<T>{
            // 1. Получаю последний элемент из стека
            val element =  stack.pop()
            // 2. Иду в самую нижнюю левую часть
            element.leftForeach{ item, _->
                stack.push(item)
            }
            // 3. Иду в самую нижнюю правую часть
            element.rightForeach{ item, index->
                if (index!=0){
                    stack.push(item)
                }
            }
            // 4. Возвращаю текущий элемент
            return  stack.pop()
        }

        fun inorderTraversal(): Node<T>{
            // 1. Получаю последний элемент из стека
            val element =  stack.pop()
            // 2. Иду в самую нижнюю левую часть
            element.leftForeach{ item, _->
                stack.push(item)
            }
            // 3. Забираю текущий элемент
            val currentElement =  stack.pop()
            // 4. Если есть правый элемент то вношу его в стек
            currentElement.right?.let {
                stack.push(it)
            }
            // 5. Возвращаю текущий элемент
            return currentElement
        }
    }
    companion object{
        data class Node<T: Comparable<T>>(val key: T, var left: Node<T>? = null, var right: Node<T>? = null)
        enum class TraversalType{
            INORDER,
            PREORDER,
            POSTORDER
        }
        fun <T: Comparable<T>>Node<T>.leftForeach(func:(Node<T>,index: Int)->Unit){
            var lastElement: Node<T>? = this@leftForeach
            var index = 0
            while (lastElement != null){
                func(lastElement, index)
                lastElement = lastElement.left
                index++
            }
        }
        fun <T: Comparable<T>>Node<T>.rightForeach(func:(Node<T>,index: Int)->Unit){
            var lastElement: Node<T>? = this@rightForeach
            var index = 0
            while (lastElement != null){
                func(lastElement, index)
                lastElement = lastElement.right
                index++
            }
        }
    }
}