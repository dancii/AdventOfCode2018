import java.io.File

fun main(args: Array<String>) {
    var input = File("./input.txt").readLines()
    val fabric: Array<IntArray> = Array(1000, {IntArray(1000)})

    input.map { claim ->
        val claimNoSpace = claim.replace("\\s".toRegex(), "")
        val temp = claimNoSpace.split("@")
        val id = temp[0].replace("#", "").toInt()
        val cordsAndSize = temp[1].split(":")
        val cords = cordsAndSize[0].split(",")
        val size = cordsAndSize[1].split("x")

        val cordsX = cords[0].toInt()
        val cordsY = cords[1].toInt()

        val sizeW = size[0].toInt()
        val sizeH = size[1].toInt()

        for (x in cordsX..(cordsX+sizeW-1)) {
            for (y in cordsY..(cordsY+sizeH-1)) {
                if (fabric[x][y] == 0) {
                    fabric[x][y] = id
                } else {
                    fabric[x][y] = -1
                }
            }
        }
    }

    var overlappedFabricCount = 0
    for(x in fabric) {
        for(y in x) {
            if (y == -1) {
                overlappedFabricCount+=1
            }
        }
    }

    println("Part one answer: $overlappedFabricCount")

    partTwo(input, fabric)

}

fun partTwo(input: List<String>, fabric: Array<IntArray>) {
   outter@ for (claim in input) {
        val claimNoSpace = claim.replace("\\s".toRegex(), "")
        val temp = claimNoSpace.split("@")
        val id = temp[0].replace("#", "").toInt()
        val cordsAndSize = temp[1].split(":")
        val cords = cordsAndSize[0].split(",")
        val size = cordsAndSize[1].split("x")

        val cordsX = cords[0].toInt()
        val cordsY = cords[1].toInt()

        val sizeW = size[0].toInt()
        val sizeH = size[1].toInt()

        for (x in cordsX..(cordsX+sizeW-1)) {
            for (y in cordsY..(cordsY+sizeH-1)) {
                if (fabric[x][y] == -1) {
                    continue@outter
                }
            }
        }
        println("Part two answer: $id")
    }
}