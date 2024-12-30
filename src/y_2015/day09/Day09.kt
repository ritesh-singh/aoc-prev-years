package y_2015.day09

import Env
import println
import readInput

private class Day09(env: Env) {
    private val input = readInput("y_2015/day09", if (env == Env.TEST) "Day09_test" else "Day09")

    private val cityMap = buildMap<String,MutableList<Pair<String,Int>>> {
        input.forEach {
            val (fT, cost) = it.split("=").map { it.trim() }
            val (f,s) = fT.split("to").map { it.trim() }
            computeIfAbsent(f){ mutableListOf() }.add(Pair(s,cost.toInt()))
            computeIfAbsent(s){ mutableListOf() }.add(Pair(f,cost.toInt()))
        }
    }

    private fun tripCost(
        source: String,
        costList: MutableList<Int>,
        cost:Int,
        visited: MutableSet<String>,
    ) {
        visited.add(source)
        if (visited.size == cityMap.keys.size)
            costList.add(cost)

        for (neigh in cityMap[source]!!){
            if (visited.contains(neigh.first))
                continue
            tripCost(
                source = neigh.first,
                costList = costList,
                cost = cost + neigh.second,
                visited = visited
            )
            visited.remove(neigh.first)
        }
    }

    fun routeDist(short:Boolean):Int{
        val costList = mutableListOf<Int>()
        for (city in cityMap.keys){
            tripCost(source = city, costList = costList, cost = 0, visited = mutableSetOf())
        }
        return if (short) costList.min() else costList.max()
    }
}

fun main() {
    Day09(env = Env.REAL).routeDist(short = true).println()
    Day09(env = Env.REAL).routeDist(short = false).println()
}
