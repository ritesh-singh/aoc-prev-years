package y_2015.day06

import Coord
import Env
import println
import readInput

private class Day06(env: Env = Env.REAL) {
    private val input = readInput("y_2015/day06", if (env == Env.TEST) "Day06_test" else "Day06")

    private enum class ACTION {
        TOGGLE, TURN_ON, TURN_OFF
    }

    private fun String.action(): Triple<ACTION, Coord, Coord> {
        when {
            startsWith("turn on") -> {
                substringAfter("turn on").trim()
                    .split("through").map { it.trim() }
                    .let {
                        val (x1, y1) = it[0].split(",").map { it.trim().toInt() }
                        val (x2, y2) = it[1].split(",").map { it.trim().toInt() }
                        return Triple(ACTION.TURN_ON, Coord(x1, y1), Coord(x2, y2))
                    }
            }

            startsWith("turn off") -> {
                substringAfter("turn off").trim()
                    .split("through").map { it.trim() }
                    .let {
                        val (x1, y1) = it[0].split(",").map { it.trim().toInt() }
                        val (x2, y2) = it[1].split(",").map { it.trim().toInt() }
                        return Triple(ACTION.TURN_OFF, Coord(x1, y1), Coord(x2, y2))
                    }
            }

            startsWith("toggle") -> {
                substringAfter("toggle").trim()
                    .split("through").map { it.trim() }
                    .let {
                        val (x1, y1) = it[0].split(",").map { it.trim().toInt() }
                        val (x2, y2) = it[1].split(",").map { it.trim().toInt() }
                        return Triple(ACTION.TOGGLE, Coord(x1, y1), Coord(x2, y2))
                    }
            }

            else -> throw IllegalArgumentException()
        }
    }

    fun howManyLightsLit():Int {
        val lightsOn = mutableSetOf<Coord>()
        input.forEach {
            it.action().let { triple ->
                val (x1, y1) = triple.second
                val (x2, y2) = triple.third
                when (triple.first) {
                    ACTION.TURN_ON -> {
                        for (x in x1..x2)
                            for (y in y1..y2)
                                lightsOn.add(Coord(x, y))
                    }
                    ACTION.TURN_OFF -> {
                        for (x in x1..x2)
                            for (y in y1..y2)
                                lightsOn.remove(Coord(x, y))
                    }
                    ACTION.TOGGLE -> {
                        for (x in x1..x2)
                            for (y in y1..y2) {
                                if (lightsOn.contains(Coord(x, y))) {
                                    lightsOn.remove(Coord(x, y))
                                    continue
                                }
                                lightsOn.add(Coord(x, y))
                            }
                    }
                }
            }
        }
        return lightsOn.size
    }

    fun totalBrightness():Int {
        val brightness = hashMapOf<Coord, Int>()
        input.forEach {
            it.action().let { triple ->
                val (x1, y1) = triple.second
                val (x2, y2) = triple.third
                when (triple.first) {
                    ACTION.TURN_ON -> {
                        for (x in x1..x2)
                            for (y in y1..y2)
                                brightness[Coord(x, y)] = brightness.getOrDefault(Coord(x, y), 0) + 1
                    }
                    ACTION.TURN_OFF -> {
                        for (x in x1..x2)
                            for (y in y1..y2)
                                brightness[Coord(x, y)] = brightness.getOrDefault(Coord(x, y), 0).let { if (it == 0) 0 else it -1 }
                    }
                    ACTION.TOGGLE -> {
                        for (x in x1..x2)
                            for (y in y1..y2)
                                brightness[Coord(x, y)] = brightness.getOrDefault(Coord(x, y), 0) + 2
                    }
                }
            }
        }
        return brightness.values.sum()
    }
}

fun main() {
    Day06().howManyLightsLit().println()
    Day06().totalBrightness().println()
}
