package y_2015.day01

import y_2015.readInput

fun main() {

    fun part1(input: List<String>) = input[0].fold(0) { acc, c -> if (c == '(') acc + 1 else acc - 1 }

    fun part2(input: List<String>): Int =
        input[0].runningFold(0) { acc, c -> if (c == '(') acc + 1 else acc - 1 }.indexOfFirst { it == -1 }

    val input = readInput("y_2015/day01", "Day01")
    println(part1(input))
    println(part2(input))
}
