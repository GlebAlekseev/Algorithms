package util
import java.io.*
import java.util.*


fun main(){
    val scanner = FastScanner(System.`in`)
    val value = scanner.nextLong()
    val str = scanner.nextLine()

    val printWriter = PrintWriter(System.out)
    printWriter.printf("%d\n", value)
    printWriter.printf("%s\n", str)
    printWriter.close()
}


class FastScanner(`in`: InputStream?) {
    private var reader: BufferedReader? = null
    private var tokenizer: StringTokenizer? = null

    init {
        reader = BufferedReader(InputStreamReader(`in`))
        tokenizer = null
    }

    operator fun next(): String {
        if (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
            tokenizer = try {
                StringTokenizer(reader!!.readLine())
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        return tokenizer!!.nextToken()
    }

    fun nextLine(): String {
        return if (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
            try {
                reader!!.readLine()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        } else tokenizer!!.nextToken("\n")
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextDouble(): Double {
        return next().toDouble()
    }

    fun nextIntArray(n: Int): IntArray {
        val a = IntArray(n)
        for (i in 0 until n) a[i] = nextInt()
        return a
    }

    fun nextLongArray(n: Int): LongArray {
        val a = LongArray(n)
        for (i in 0 until n) a[i] = nextLong()
        return a
    }
}