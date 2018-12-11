import java.io.File


fun main(args: Array<String>) {
    var coordsFile = File("./input.txt").readLines()
    var xCoords = ArrayList<Int>()
    var yCoords = ArrayList<Int>()
    var coords = ArrayList<Coord>()

    coordsFile.forEachIndexed { index, coord ->
        val tempCoord = coord.replace(" ", "").toString().split(",")
        coords.add(Coord(index+1, tempCoord[0].toInt(), tempCoord[1].toInt()))
        xCoords.add(tempCoord[0].toInt())
        yCoords.add(tempCoord[1].toInt())
    }
    //partOne(coords, xCoords, yCoords)
    partTwo(coords, xCoords, yCoords)
    
}

fun partOne(coords: ArrayList<Coord>, xCoords: ArrayList<Int>, yCoords: ArrayList<Int>) {
    val xMin: Int = 0
    val xMax: Int = xCoords.max()!!+1
    val yMin: Int = 0
    val yMax: Int = yCoords.max()!!+1

    val grid = Array(xMax){arrayOfNulls<CoordVal>(yMax)}
    
    coords.map { coord ->
        for (x in xMin..xMax-1) {
            for (y in yMin..yMax-1) {
                val manDistance = Math.abs(coord.x - x) + Math.abs(coord.y-y)
                val tempCoordVal: CoordVal? = grid[x][y]
                if (tempCoordVal == null) {
                    grid[x][y] = CoordVal(coord.key, manDistance)
                } else {
                    if (manDistance < tempCoordVal.manDistance ) {
                        grid[x][y] = CoordVal(coord.key, manDistance)
                    } else if (manDistance == tempCoordVal.manDistance) { 
                        grid[x][y] = CoordVal(-1, manDistance)
                    }
                }
            }
        }
    }
    var keyLargestArea = 0
    var largestArea = 0
    coords.map { coord ->
        var area = 0
        var key = coord.key
        outter@ for (x in xMin..xMax-1) {
            for (y in yMin..yMax-1) {
                val tempCoordVal = grid[x][y]!!
                if ((x == xMin || x == xMax-1 || y == xMin || y == yMax-1) && key == tempCoordVal.key) {
                    break@outter
                }
                if (key == tempCoordVal.key) {
                    area++
                }
            }
        }
        if (area > largestArea) {
            keyLargestArea = key
            largestArea = area
        }
    }

    println("Key: $keyLargestArea, Largest area: $largestArea")
}


fun partTwo (coords: ArrayList<Coord>, xCoords: ArrayList<Int>, yCoords: ArrayList<Int>) {
    var region = ArrayList<Int>()
    val xMin: Int = 0
    val xMax: Int = xCoords.max()!!+1
    val yMin: Int = 0
    val yMax: Int = yCoords.max()!!+1

    var regionSize = 0

    for (x in xMin..xMax-1) {
        for (y in yMin..yMax-1) {
            inner@ for (coord in coords) {
                val manDistance = Math.abs(coord.x - x) + Math.abs(coord.y-y)
                region.add(manDistance)
                if (region.sum() >= 10000) {
                    break@inner
                }
            }
            if (region.sum() < 10000) {
                regionSize++
            }
            region.clear()
        }
    }

    println(regionSize)

}

class Coord constructor(var key: Int, var x: Int, var y: Int)
class CoordVal constructor(var key: Int, var manDistance: Int)