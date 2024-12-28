package y_2015.day01

import Env
import println
import readText

private class Day01(env: Env) {
    private val input = readText("y_2015/day01", if (env == Env.TEST) "Day01_test" else "Day01")

    fun whatFloor() = input.toCharArray().fold(0) { acc: Int, c: Char -> acc + if (c == '(') 1 else -1 }

    fun whatPosition() = run {
        input.toCharArray().foldIndexed(0) { index, acc, c ->
            if (acc == -1) return@run index else acc + if (c == '(') 1 else -1
        } + 1
    }
}

fun main() {
    Day01(env = Env.REAL).whatFloor().println()
    Day01(env = Env.REAL).whatPosition().println()
}
