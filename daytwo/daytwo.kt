import java.io.File

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()
    partOne(input)
    partTwo(input)
}

fun partOne(input: List<String>) {
    var twice = 0
    var trice = 0 //LUL
    var alreadyTwice = false
    var alreadyTrice = false
    input.map { boxId ->
        alreadyTwice = false
        alreadyTrice = false
        for (c: Char in boxId) {
            val count = boxId.toCharArray().filter { char -> char == c}.count()
            if (count == 2 && !alreadyTwice) {
                twice += 1
                alreadyTwice = true
            } else if (count == 3 && !alreadyTrice) {
                trice += 1
                alreadyTrice = true
            }
        }
    }

    println(twice * trice)
}

fun partTwo(input: List<String>) {
    var missplacedCount: Int = 0
    var missplacedPos: Int = 0
    outter@ for (boxIdOne in input) {
        for (boxIdTwo in input) {
            if (boxIdOne == boxIdTwo) {
                continue
            }
            missplacedCount = 0

            for ((index, boxIdOneChar) in boxIdOne.withIndex()) {
                if (boxIdOneChar != boxIdTwo[index]) {
                    missplacedCount += 1
                    missplacedPos = index
                }
                if (missplacedCount > 1) {
                    break
                }
            }

            if (missplacedCount == 1) {
                println("BoxIdOne: "+boxIdOne+" BoxIdTwo: "+boxIdTwo+" Correct word: "+boxIdOne.removeRange(missplacedPos, missplacedPos))
                break@outter
            }
        }
    }

}