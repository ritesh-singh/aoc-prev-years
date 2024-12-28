package y_2015.day04

import Env
import println
import readText
import java.security.MessageDigest

private class Day04(env: Env) {
    private val input = readText("y_2015/day04", if (env == Env.TEST) "Day04_test" else "Day04")

    private fun String.md5(): String {
        return MessageDigest.getInstance("MD5").digest(toByteArray())
            .joinToString(""){ "%02x".format(it) }
    }

    fun lowestPositiveNumber(secretKey: String = input, zeros:String): Int {
        var number = 1
        while (true) {
            if ("$secretKey$number".md5().startsWith(zeros))
                return number
            number++
        }
    }
}

fun main() {
    Day04(env = Env.REAL).lowestPositiveNumber(zeros = "00000").println()
    Day04(env = Env.REAL).lowestPositiveNumber(zeros = "000000").println()
}
