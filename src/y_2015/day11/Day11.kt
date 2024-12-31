package y_2015.day11

import Env
import println
import readText

private class Day11(env: Env) {
    private val input = readText("y_2015/day11", if (env == Env.TEST) "Day11_test" else "Day11")

    private fun String.valid(): Boolean {
        if (!windowed(3).any { it.zipWithNext().all { it.second - it.first == 1 } })
            return false
        if (contains("""[iol]""".toRegex())) return false
        if (windowed(2).toSet().count { it.first() == it.last() } < 2) return false
        return true
    }

    private fun nextPass(orig:String):String {
        var cur = orig
        while (true){
            val newStr = cur.reversed().toCharArray()
            for (index in newStr.indices) {
                if (newStr[index] < 'z') {
                    newStr[index] = newStr[index] + 1
                    break
                }
                newStr[index] = 'a'
            }
            cur = newStr.reversed().joinToString("")
            if (cur.valid()) return cur
        }
    }

    fun nextPass(part1: Boolean): String {
        if (part1)
            return nextPass(input)
        return nextPass(nextPass(input))
    }
}

fun main() {
    Day11(env = Env.REAL).nextPass(part1 = true).println()
    Day11(env = Env.REAL).nextPass(part1 = false).println()
}
