import java.io.File

fun main(args: Array<String>) {
    var polymer = File("./input.txt").readText()
    
    println(reaction(polymer))
    println(partTwo(polymer))
}

fun reaction(s: String): Int {
    var polymer = s
    var done = false
    while (!done) {
        for (i in 0..polymer.length-1) {
            if ((i+1) == polymer.length) {
                done = true
                break
            }
            if ((polymer[i] == (polymer[i+1]-32)) || (polymer[i] == (polymer[i+1]+32))) {
                polymer = polymer.removeRange(i, i+2)
                break
            }
        }
    }
    return polymer.length
}

fun partTwo(s: String): Int {
    var c: Char = 'A'
    var shortestPolymer = 100000000

    while (c <= 'Z') {
        var polymer = s
        polymer = polymer.replace(c.toString(), "")
        polymer = polymer.replace(c.toString().toLowerCase(), "")
        var length = reaction(polymer)
        if (shortestPolymer > length) {
            shortestPolymer = length
        }
        ++c
    }
    return shortestPolymer
}