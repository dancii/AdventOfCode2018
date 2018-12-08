import java.io.File
import java.util.Arrays

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()

    val guards: ArrayList<Guard> = ArrayList<Guard>()

    val sortedInput = input.sortedBy { it }
    // sortedInput.map { it ->
    //     println(it)
    // }
    var latestGuardId = 0
    var timeAsleep = 0
    var timeAwake = 0
    sortedInput.map { it ->
        val date = it.substring(1, 17)
        val text = it.substring(18)

        if (text.contains("Guard")) {
            latestGuardId = text.split(" ")[2].substring(1).toInt()
        } else if (text.contains("falls")) {
            timeAsleep = date.substring(14, 16).toInt()
        } else if (text.contains("wakes")) {
            timeAwake = date.substring(14, 16).toInt()
            var guard = Guard()
            var guardExists = false
            for (tempGuard in guards) {
                if (tempGuard.guardId == latestGuardId) {
                    guard = tempGuard
                    guardExists = true
                }
            }
            guard.guardId = latestGuardId
            guard.totalSleepTime+=(timeAwake-timeAsleep)
            for (i in timeAsleep..(timeAwake-1)) {
                guard.sleepTimeLine[i]++
            }
            if (!guardExists) {
                guards.add(guard)
            }

            /* println("Guard id: ${guard.guardId}")
            println("Asleep: $timeAsleep")
            println("Awake: $timeAwake")
            println("Guard sleep time: ${guard.totalSleepTime}")
            println("Guard sleep time: ${Arrays.toString(guard.sleepTimeLine)}")*/
        } 
    }

    val guardWithMostSleepTime: Guard? = guards.maxBy { it.totalSleepTime }
    val guardIdWithMostSleepTime: Int? = guardWithMostSleepTime?.guardId

    val minuteTheGuardSpentMostTimeAsleep: Int? = guardWithMostSleepTime?.sleepTimeLine?.maxBy { it }

    val answer = guardIdWithMostSleepTime?.times(minuteTheGuardSpentMostTimeAsleep!!)
    println(answer)
}

class Guard {
    var guardId: Int = 0
    val sleepTimeLine: IntArray = IntArray(60)
    var totalSleepTime: Int = 0
}