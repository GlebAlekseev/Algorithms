package toff.approximation.B2

import java.io.File
import kotlin.math.exp
import kotlin.random.Random

fun main() {
//    val rand = Random(7)
//    val list = mutableListOf<Int>()
//    File("./toff/approximation/B.txt").forEachLine {
//        it.trim().split(Regex(" +")).forEach {
//            list.add(it.toInt())
//        }
//    }
//    // Парсинг матрицы
//    val distanceMatrixRaw = list.windowed(312, 312)
//    val distanceMatrix = distanceMatrixRaw.map { it.toMutableList() }.toMutableList()
//    distanceMatrix.add(0, MutableList(312) { 0 })
//    distanceMatrix.forEach {
//        it.add(0, 0)
//    }
//
//    // Метод адаптивного отжига
//    val result = (1..312).toMutableList()
//    var t = 10.0
//    var currentMin = f(distanceMatrix, result)
//    var i = 0
//    var coolingRate = 0.99
//    var previousMin = currentMin
//    while (i < 1000000) {
//        t *= coolingRate
//        val r1 = rand.nextInt(0, result.size)
//        val r2 = rand.nextInt(0, result.size)
//
//        result[r1] = result[r2].also { result[r2] = result[r1] }
//
//        val value = f(distanceMatrix, result)
//
//        if (value < currentMin || exp((currentMin - value) / t) > rand.nextInt(0, 2)) {
//            currentMin = value
//        } else {
//            result[r1] = result[r2].also { result[r2] = result[r1] }
//        }
//        i++
//        if (i % 10 == 0) {
//            if (currentMin < previousMin) {
//                coolingRate += 0.001
//            } else {
//                coolingRate -= 0.001
//            }
//            coolingRate = coolingRate.coerceIn(0.001, 1.0)
//            previousMin = currentMin
//        }
//    }

//    println(result.map { it }.joinToString(" "))
//    println(f(distanceMatrix, result))

    println("298 131 209 146 161 216 200 258 295 140 123 89 126 176 256 160 95 130 264 63 134 133 281 231 150 203 33 44 118 107 173 218 92 228 166 233 222 81 108 249 197 282 111 22 253 270 79 278 269 185 191 172 284 279 305 36 167 31 156 143 68 121 103 23 204 20 180 247 234 275 190 72 101 100 288 51 276 12 157 250 14 64 54 109 217 6 251 285 3 202 263 299 266 39 179 163 178 117 210 91 50 194 177 83 215 80 221 201 41 223 171 55 271 230 59 248 290 148 120 119 245 242 189 124 224 214 135 58 96 71 114 154 35 175 122 30 170 168 28 112 195 65 274 127 52 303 208 184 220 86 115 37 138 42 227 182 129 141 116 174 73 262 196 32 104 259 132 142 226 94 152 310 2 47 66 265 60 147 139 10 53 297 267 312 11 162 151 277 69 97 287 232 192 56 110 260 306 294 18 128 181 283 198 302 85 4 246 77 300 236 74 62 212 7 1 292 16 238 84 286 102 211 237 187 93 199 311 240 241 186 26 88 308 252 273 206 235 87 225 268 99 144 105 57 188 136 164 296 243 13 301 29 272 289 38 67 159 207 145 155 307 40 34 46 205 15 19 229 113 169 98 254 43 137 24 213 5 183 8 90 70 309 82 45 293 239 193 153 244 17 48 219 291 25 261 27 255 257 75 49 78 158 165 106 21 125 9 76 304 61 280 149")
    // 65k
}

// принимает матрицу и текущий стейт.. на освнове траектории вычисляет путь
fun f(distanceMatrix: List<List<Int>>, way: List<Int>): Long {
    var sum = 0L
    for (i in 0 until way.size - 1) {
        val distance = distanceMatrix[way[i]][way[i + 1]]
        sum += distance
    }
    return sum
}