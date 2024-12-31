package y_2015.day12

import Env
import kotlinx.serialization.json.*
import println
import readText

private class Day12(env: Env) {
    private val input = readText("y_2015/day12", if (env == Env.TEST) "Day12_test" else "Day12")

    fun sumAllNumbers() = """-?\d+""".toRegex().findAll(input).map { it.value }.sumOf { it.toLong() }

    private fun sumWithoutRed(jsonElement: JsonElement): Int {
        return when(jsonElement) {
            is JsonPrimitive -> jsonElement.intOrNull ?: 0
            is JsonArray -> jsonElement.sumOf { sumWithoutRed(it) }
            is JsonObject -> {
                if (jsonElement.values.contains(JsonPrimitive("red"))) 0
                else jsonElement.values.sumOf { sumWithoutRed(it) }
            }
        }
    }

    fun sumWithoutRed() = sumWithoutRed(Json.parseToJsonElement(input))

}

fun main() {
    Day12(env = Env.REAL).sumAllNumbers().println()
    Day12(env = Env.REAL).sumWithoutRed().println()
}
