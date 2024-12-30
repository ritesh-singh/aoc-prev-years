package y_2015.day07

import Env
import println
import readInput

private class Day07(env: Env) {
    private val input = readInput("y_2015/day07", if (env == Env.TEST) "Day07_test" else "Day07")

    private val gate = buildMap {
        input.forEach {
            val (l, r) = it.split("->").map { it.trim() }
            put(r, l)
        }
    }.toMutableMap()

    private fun findSignal(source: String, seen:MutableMap<String,UShort>):UShort {
        if (seen.contains(source)) return seen[source]!!
        if (source.toUShortOrNull() != null) return source.toUShort()
        return when {
            gate[source]!!.contains("LS") -> {
                val (l,_,r) = gate[source]!!.split(" ").map { it.trim() }
                ((findSignal(l,seen).also { seen[l]  = it }).toInt() shl (findSignal(r,seen)).also { seen[r]  = it }.toInt()).toUShort()
            }
            gate[source]!!.contains("RS") -> {
                val (l,_,r) = gate[source]!!.split(" ").map { it.trim() }
                ((findSignal(l,seen).also { seen[l]  = it }).toInt() shr (findSignal(r,seen)).also { seen[r]  = it }.toInt()).toUShort()
            }
            gate[source]!!.contains("OR") -> {
                val (l,_,r) = gate[source]!!.split(" ").map { it.trim() }
                ((findSignal(l,seen).also { seen[l]  = it }).toInt() or (findSignal(r,seen)).also { seen[r]  = it }.toInt()).toUShort()
            }
            gate[source]!!.contains("AND") -> {
                val (l,_,r) = gate[source]!!.split(" ").map { it.trim() }
                ((findSignal(l,seen).also { seen[l]  = it }).toInt() and (findSignal(r,seen)).also { seen[r]  = it }.toInt()).toUShort()
            }
            gate[source]!!.contains("NOT") -> {
                val r = gate[source]!!.removePrefix("NOT").trim()
                findSignal(r,seen).also { seen[r] = it }.inv()
            }
            else -> findSignal(gate[source]!!,seen).also { seen[source] = it }
        }
    }

    fun aValue() = findSignal("a", mutableMapOf())
    fun aValuePart2():UShort {
        gate["b"] = aValue().toString()
        return aValue()
    }
}

fun main() {
    Day07(env = Env.REAL).aValue().println()
    Day07(env = Env.REAL).aValuePart2().println()
}
