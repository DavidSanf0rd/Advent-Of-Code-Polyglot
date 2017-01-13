import java.io.File

/**
 * Created by sanf0rd on 06/01/17.
 */
 
enum class Turn {
    Left, Right
}

enum class Direction {
    Vertical, Horizontal
}

enum class Sense(val direction: Direction, val factor: Int) {
    NORTH(Direction.Vertical, 1),
    SOUTH(Direction.Vertical, -1),
    WEST(Direction.Horizontal, 1),
    EAST(Direction.Horizontal, -1);

    fun next(requestedTurn: Turn): Sense {
        var nextDirection: Sense

        when (this) {
            NORTH -> nextDirection = if (requestedTurn == Turn.Right) WEST else EAST
            SOUTH -> nextDirection = if (requestedTurn == Turn.Right) EAST else WEST
            EAST  -> nextDirection = if (requestedTurn == Turn.Right) NORTH else SOUTH
            WEST -> nextDirection = if (requestedTurn == Turn.Right) SOUTH else NORTH
        }

        return nextDirection
    }
}

fun main(args: Array<String>) {

    var currentSense = Sense.NORTH

    var x = 0
    var y = 0

    val text = File("input.txt").readText()

    val splited = text.split(", ")

    for (value in splited) {
        val nextTurn = if (value.startsWith("R")) Turn.Right else Turn.Left
        val distance = value.replace("R", "").replace("L", "").toInt()

        val nextSense = currentSense.next(nextTurn)

        if (nextSense.direction == Direction.Vertical) {
            y += distance * nextSense.factor
        }else {
            x += distance * nextSense.factor
        }

        currentSense = nextSense
    }
    print(Math.abs(x) + Math.abs(y))
}
