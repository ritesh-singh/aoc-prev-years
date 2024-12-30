package y_2015.day10

import Env
import println
import readText

private class Day10(env: Env) {
    private val input = readText("y_2015/day10", if (env == Env.TEST) "Day10_test" else "Day10")

    private fun totalLength(number: String, count: Int): Int {
        if (count == 0) return number.length
        val next = number.fold(mutableListOf<Pair<Int, Char>>()) { acc, c ->
            when {
                acc.isEmpty() || acc.last().second != c -> acc.add(Pair(1, c))
                acc.last().second == c -> acc[acc.lastIndex] = Pair(acc.last().first + 1, c)
            }
            acc
        }.joinToString("") { "${it.first}${it.second}" }
        return totalLength(next, count - 1)
    }

    fun length(count: Int) = totalLength(input,count)

}

fun main() {
    Day10(env = Env.REAL).length(40).println()
    Day10(env = Env.REAL).length(50).println()
}
