import java.io.File

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()
    var freq: Int = 0
    val freqHistory = HashSet<Int>()
    var firstRun = true

    while(true) {
      input.map{ value ->
      
        freq = freq.plus(value.toInt())

        if (freqHistory.contains(freq)) {
          println("Freq part two: "+freq)
          return
        }
        freqHistory.add(freq)
      }

      if (firstRun) {
        firstRun = false
        println("Freq part one: "+freq)
      }
    }
}