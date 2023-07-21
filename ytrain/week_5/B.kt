package ytrain.week_5

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val s = readln()

    val totalUnique = s.toSet().size
    var ans = 0

    for (uniqueLim in 1..totalUnique) {
        var left = 0
        var right = 0
        val cnt = mutableMapOf(s[left] to 1)
        var kCntChars = 0
        if (k==1) kCntChars = 1
        var nowUnique = 1

        while (right < n) {
            if (nowUnique > uniqueLim) {
                if (cnt[s[left]] == k) {
                    kCntChars--
                }
                cnt[s[left]] = cnt.getOrDefault(s[left], 0) - 1
                if (cnt[s[left]] == 0) {
                    nowUnique--
                }
                left++
            }else {
                if (kCntChars == uniqueLim) {
                    ans = maxOf(ans, right - left + 1)
                }
                right++
                if (right < n) {
                    cnt[s[right]] = cnt.getOrDefault(s[right], 0) + 1
                    if (cnt[s[right]] == k) {
                        kCntChars++
                    }
                    if (cnt[s[right]] == 1) {
                        nowUnique++
                    }
                }
            }
        }
    }
    println(ans)
}
