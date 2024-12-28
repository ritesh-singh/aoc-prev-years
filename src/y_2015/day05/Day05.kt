package y_2015.day05

import Env
import println
import readInput

private class Day05(env: Env) {
    private val input = readInput("y_2015/day05", if (env == Env.TEST) "Day05_test" else "Day05")

    private fun String.niceString():Boolean{
        if (zipWithNext().map { "${it.first}${it.second}" }.any { listOf("ab", "cd", "pq", "xy").contains(it) })
            return false
        if (count { it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u' } < 3)
            return false
        if (!zipWithNext().any { it.first == it.second }) return false
        return true
    }

    private fun String.niceStringNewRule():Boolean {
        if (!(zipWithNext { a, b ->  "$a$b"}.distinct().any { it.toRegex().findAll(this).count() >=2 }))
            return false

        if (!(windowed(3).any { it.first() == it.last() }))
            return false
        return true
    }

    fun niceStrings() = input.count { it.niceString() }
    fun niceStringsNewRules() = input.count { it.niceStringNewRule() }
}

fun main() {
    Day05(env = Env.REAL).niceStrings().println()
    Day05(env = Env.REAL).niceStringsNewRules().println()
}
