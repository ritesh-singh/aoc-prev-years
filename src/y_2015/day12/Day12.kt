package y_2015.day12

import Env
import println
import readText

private class Day12(env: Env) {
    private val input = readText("y_2015/day12", if (env == Env.TEST) "Day12_test" else "Day12")

    fun sumAllNumbers() = """-?\d+""".toRegex().findAll(input).map { it.value }.sumOf { it.toLong() }

    fun sumWithoutRedObject() {

    }
}

fun main() {
    Day12(env = Env.REAL).sumAllNumbers().println()
    Day12(env = Env.REAL).sumWithoutRedObject()
}
