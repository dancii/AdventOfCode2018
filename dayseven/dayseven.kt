import java.io.File

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()

    val instructions: ArrayList<Instruction> = ArrayList<Instruction>()
    val stepsOne = mutableSetOf<String>()
    val stepsTwo = mutableSetOf<String>()
    var result = ""
    for (instructionTemp in input) {
        val step = instructionTemp.substring(36,37)
        val stepBefore = instructionTemp.substring(5,6)
        stepsOne.add(step)
        stepsTwo.add(stepBefore)
        val existingInstruction = instructions.find { it -> it.step == step }
        val instruction = if (existingInstruction != null) existingInstruction else Instruction()
        instruction.stepsBefore.add(stepBefore)
        if (existingInstruction == null) {
            instruction.step = step
            instructions.add(instruction)
        }
    }

    var startStep = stepsTwo.minus(stepsOne).elementAt(0)
    result+=startStep
    while (instructions.size > 0) {
        
        for (instruction in instructions) {
            instruction.stepsBefore.remove(startStep)
        }

        instructions.sortWith(compareByDescending<Instruction>{ it.stepsBefore.size == 0 }.thenBy {it.step})
        startStep = instructions.removeAt(0).step
        result+=startStep
    }
    
    println(result)
    
}

class Instruction() {
    var step: String = ""
    var stepsBefore: ArrayList<String> = ArrayList<String>()
}