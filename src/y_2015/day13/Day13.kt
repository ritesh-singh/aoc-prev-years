package y_2015.day13

import Env
import com.github.shiguruikai.combinatoricskt.Combinatorics
import println
import readInput

private class Day13(env: Env) {
    private val input = readInput("y_2015/day13", if (env == Env.TEST) "Day13_test" else "Day13")

    private val invited = mutableSetOf<String>()
    private val hapMap = buildMap {
        input.forEach {
            val all = it.split(" ").map { it.trim() }
            put(
                Pair(all.first(), all.last().dropLast(1)),
                if (all[2] == "gain") all[3].toInt() else -all[3].toInt()
            )
            invited.add(all.first())
            invited.add(all.last().dropLast(1))
        }
    }

    fun totalHappiness(): Int {
        return Combinatorics.permutations(invited).map {
            it.zipWithNext().toMutableList().run {
                add(Pair(it.last(), it.first()))
                this
            }.sumOf {
                (hapMap[it]!! + hapMap[Pair(it.second, it.first)]!!)
            }
        }.maxOf { it }
    }

    fun includingMe():Int {
        invited.add("R")
        return Combinatorics.permutations(invited).map {
            it.zipWithNext().toMutableList().run {
                add(Pair(it.last(), it.first()))
                this
            }.sumOf {
                (hapMap[it]?:0) + (hapMap[Pair(it.second, it.first)]?:0)
            }
        }.maxOf { it }
    }
}

fun main() {
    Day13(env = Env.REAL).totalHappiness().println()
    Day13(env = Env.REAL).includingMe().println()
}
