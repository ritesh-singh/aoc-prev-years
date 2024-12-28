import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(yearDay: String, name: String):List<String>  {
    return Path("src/$yearDay/$name.txt").readLines()
}


fun readText(yearDay: String, name: String):String  {
    return Path("src/$yearDay/$name.txt").readText().trim()
}

fun Any?.println() = println(this)

fun printGrid(grid: Any) {
    when (grid) {
        is List<*> -> {
            if (grid.isEmpty()) throw IllegalStateException("Can't print empty grid")
            when(val fE = grid.first()){
                is String -> {
                    for (row in grid) println(row)
                }
                is List<*> -> {
                    for (row in grid)
                        println((row as List<*>).joinToString(" ") { it.toString() })
                }
                is CharArray -> {
                    for (row in grid) println(row.toString())
                }
                else -> throw IllegalArgumentException("Unsupported grid type")
            }
        }
    }
}

data class Coord(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x, $y)"
    }
}

fun Coord.inRange(grid: List<String>) = x in grid.indices && y in grid[0].indices

fun Coord.up() = Coord(x - 1, y)
fun Coord.down() = Coord(x + 1, y)
fun Coord.left() = Coord(x, y - 1)
fun Coord.right() = Coord(x, y + 1)
fun Coord.all() = listOf(up(),down(),left(),right())

enum class Direction {
    E,W,N,S
}

enum class Env {
    TEST, REAL
}


