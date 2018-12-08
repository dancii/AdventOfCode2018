import java.io.File
import java.util.Arrays

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()

    val guards: ArrayList<Guard> = ArrayList<Guard>()

    val sortedInput = input.sortedBy { it }
    var latestGuardId = 0
    var timeAsleep = 0

    sortedInput.map { it ->
        val date = it.substring(1, 17)
        val text = it.substring(18)

        if (text.contains("Guard")) {
            latestGuardId = text.split(" ")[2].substring(1).toInt()
        } else if (text.contains("falls")) {
            timeAsleep = date.substring(14, 16).toInt()
        } else if (text.contains("wakes")) {

            var timeAwake = date.substring(14, 16).toInt()
            var existingGuard = guards.find { guard -> guard.guardId == latestGuardId }

            var guard = if (existingGuard != null) existingGuard else Guard()

            guard.guardId = latestGuardId
            guard.totalSleepTime+=(timeAwake-timeAsleep)

            for (i in timeAsleep..(timeAwake-1)) {
                guard.sleepTimeLine[i]++
            }

            if (existingGuard == null) {
                guards.add(guard)
            }
        } 
    }
    
    println("Answer part one: ${partOne(guards)}")
    println("Answer part two: ${partTwo(guards)}")
}

fun partOne(guards: ArrayList<Guard>): Int? {
    val guardWithMostSleepTime: Guard? = guards.maxBy { it.totalSleepTime }
    val guardIdWithMostSleepTime: Int? = guardWithMostSleepTime?.guardId

    val minuteTheGuardSpentMostTimeAsleep = guardWithMostSleepTime?.let {
        guardWithMostSleepTime.sleepTimeLine.indexOf(guardWithMostSleepTime.sleepTimeLine.max()!!)
    }

    val answer = minuteTheGuardSpentMostTimeAsleep?.let {
        guardIdWithMostSleepTime?.times(minuteTheGuardSpentMostTimeAsleep)
    }

    return answer
}

fun partTwo(guards: ArrayList<Guard>): Int? {
    var maxMinuteValue = 0
    var guard: Guard? = null
    guards.map { it ->
        val maxMinute = it.sleepTimeLine.max()!!
        if (maxMinute > maxMinuteValue) {
            maxMinuteValue = maxMinute
            guard = it
        }
    }
    var answer = guard?.let { it ->
        it.guardId.times(it.sleepTimeLine.indexOf(maxMinuteValue))
    }
    return answer
}

class Guard {
    var guardId: Int = 0
    val sleepTimeLine: IntArray = IntArray(60)
    var totalSleepTime: Int = 0
}