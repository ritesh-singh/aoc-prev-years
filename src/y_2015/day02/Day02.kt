package y_2015.day02

import Env
import println
import readInput

private class Day02(env: Env) {
    private val input = readInput("y_2015/day02", if (env == Env.TEST) "Day02_test" else "Day02")

    fun totalSqFtWrappingPaper() = input.sumOf {
        val (l, w, h) = it.split("x").map { it.trim().toLong() }
        (2 * l * w) + (2 * w * h) + (2 * h * l) + listOf(l * w, w * h, h * l).min()
    }

    fun totalFtRibbon() = input.sumOf {
        val (l, w, h) = it.split("x").map { it.trim().toLong() }
        (l * w * h) + listOf(l, w, h).sorted().take(2).map { it * 2 }.sum()
    }

}

fun main() {
    Day02(Env.REAL).totalSqFtWrappingPaper().println()
    Day02(Env.REAL).totalFtRibbon().println()
}
