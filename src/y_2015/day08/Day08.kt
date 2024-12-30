package y_2015.day08

import Env
import println
import readInput

private class Day08(env: Env) {
    private val input = readInput("y_2015/day08", if (env == Env.TEST) "Day08_test" else "Day08")

    fun total(): Int {
        return input.sumOf {
            it.length -
                    it.removePrefix("\"").removeSuffix("\"")
                        .replace("\\\\", "\\")
                        .replace("\\\"", "\"")
                        .replace("""\\x[a-fA-F0-9]{2}""".toRegex(), "1")
                        .length
        }
    }

    fun totalPart2():Int {
        return input.sumOf {
            it.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .length + 2 - it.length
        }
    }
}

fun main() {
    Day08(env = Env.REAL).total().println()
    Day08(env = Env.REAL).totalPart2().println()
}
