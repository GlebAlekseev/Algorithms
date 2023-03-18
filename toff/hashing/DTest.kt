package toff.hashing.DTest

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import toff.hashing.D.PolynomialHashing
import toff.hashing.D.isPalindrome


@RunWith(Parameterized::class)
class Test2(
    private val text: String
) {
    @Test
    fun test() {
        val textPolynomialHashing = PolynomialHashing(text)
        val reverseTextPolynomialHashing = PolynomialHashing(text.reversed())
        var counter1 = 0
        for (i in 1..text.length) {
            for (j in i downTo 1) {
                //j..i
                val result = isPalindrome(textPolynomialHashing, reverseTextPolynomialHashing, j, i)
                if (result) counter1++
            }
        }

        var counter2 = 0
        for (i in text.indices){
            val maxDistance =  if (i < text.length/2) i else text.length - i - 1
            var l = 0
            var r = maxDistance
            while (l <= r){
                val middle = (l + r) / 2
                if (isPalindrome(
                        textPolynomialHashing,
                        reverseTextPolynomialHashing,
                        i - middle + 1,
                        i + middle + 1
                    )
                ) l = middle + 1 else r = middle - 1
            }
            counter2 += r + 1
        }
        for (i in 1 until text.length){
            val maxDistance =  if (i <= text.length/2) i - 1 else text.length - i - 1
            var l = 0
            var r = maxDistance
            while (l <= r){
                val middle = (l + r) / 2
                if (isPalindrome(
                        textPolynomialHashing,
                        reverseTextPolynomialHashing,
                        i - 1 - middle + 1,
                        i + middle + 1
                    )
                ) l = middle + 1 else r = middle - 1
            }
            counter2 += r + 1
        }
        println("\tcounter1=$counter1 \n\tcounter2=$counter2")
        Assert.assertEquals(counter1, counter2)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            val test1 = StringBuilder()
            repeat(710){
                test1.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            }
            return listOf(
                arrayOf(test1.toString()),
            )
        }
    }
}
