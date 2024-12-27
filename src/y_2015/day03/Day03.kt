package y_2015.day03

import Coord
import Env
import down
import left
import println
import readText
import right
import up

private class Day03(env: Env) {
    private val input = readText("y_2015/day03", if (env == Env.TEST) "Day03_test" else "Day03")

    private var startS = Coord(0, 0)
    private var startRbS = Coord(0, 0)

    fun atLeastOnePresent(): Int {
        return buildMap {
            putIfAbsent(startS, 1)
            input.toCharArray().forEach {
                startS = when (it) {
                    '^' -> startS.up().also { putIfAbsent(it, 1) }
                    'v' -> startS.down().also { putIfAbsent(it, 1) }
                    '<' -> startS.left().also { putIfAbsent(it, 1) }
                    '>' -> startS.right().also { putIfAbsent(it, 1) }
                    else -> throw IllegalArgumentException()
                }
            }
        }.keys.size
    }

    fun santaAndRoBoSanta(): Int {
        return buildMap {
            putIfAbsent(startS, 1)
            input.toCharArray().forEachIndexed { index, c ->
                when (c) {
                    '^' -> {
                        if (index % 2 == 0) {
                            startS = startS.up().also { putIfAbsent(it, 1) }
                        } else {
                            startRbS = startRbS.up().also { putIfAbsent(it, 1) }
                        }
                    }
                    'v' -> {
                        if (index % 2 == 0) {
                            startS = startS.down().also { putIfAbsent(it, 1) }
                        } else {
                            startRbS = startRbS.down().also { putIfAbsent(it, 1) }
                        }
                    }
                    '<' -> {
                        if (index % 2 == 0) {
                            startS = startS.left().also { putIfAbsent(it, 1) }
                        } else {
                            startRbS = startRbS.left().also { putIfAbsent(it, 1) }
                        }
                    }
                    '>' -> {
                        if (index % 2 == 0) {
                            startS = startS.right().also { putIfAbsent(it, 1) }
                        } else {
                            startRbS = startRbS.right().also { putIfAbsent(it, 1) }
                        }
                    }
                    else -> throw IllegalArgumentException()
                }
            }
        }.keys.size
    }
}

fun main() {
    Day03(Env.REAL).atLeastOnePresent().println()
    Day03(Env.REAL).santaAndRoBoSanta().println()
}
