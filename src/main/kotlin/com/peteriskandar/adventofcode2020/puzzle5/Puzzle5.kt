package com.peteriskandar.adventofcode2020.puzzle5

import com.peteriskandar.adventofcode2020.puzzle3.Puzzle3
import com.peteriskandar.adventofcode2020.util.InputReader
import com.peteriskandar.adventofcode2020.util.Puzzle
import kotlin.math.ceil
import kotlin.math.floor

data class BoardingData(val row: Int, val col: Int, val id: Int)

class Puzzle5(private val input: List<String>) : Puzzle<List<BoardingData>> {
    //
    override fun processA(): List<BoardingData> {
        val list = mutableListOf<BoardingData>()
        for (line in input) {
            val boardingData = processLine(line)
            list.add(boardingData)
        }

        return list
    }

    private fun processLine(line: String): BoardingData {
        var currentLowerRow = 0
        var currentUpperRow = 127

        var currentLowerColumn = 0
        var currentUpperColumn = 7

        for (c in line) {
            val tempRow = (currentLowerRow + currentUpperRow) / 2.0
            val tempCol = (currentLowerColumn + currentUpperColumn) / 2.0
            when (c) {
                'F' -> {
                    currentUpperRow = floor(tempRow).toInt()
                }
                'B' -> {
                    currentLowerRow = ceil(tempRow).toInt()
                }
                'L' -> {
                    currentUpperColumn = floor(tempCol).toInt()
                }
                'R' -> {
                    currentLowerColumn = ceil(tempCol).toInt()
                }
            }
        }
        return BoardingData(currentUpperRow, currentUpperColumn, (currentUpperRow * 8) + currentUpperColumn)
    }

    override fun processB(): List<BoardingData> {
        TODO("Not yet implemented")
    }
}

fun findMissing (ar:List<Int>, size: Int): Int {
    var a = 0
    var b = size - 1
    var mid = 0

    while((b - a) > 1) {
        mid = (a + b) / 2
        if((ar[a] - a) != (ar[mid] - mid))
            b = mid
        else if((ar[b] - b) != (ar[mid] - mid)) {
            a = mid
        }
    }
    return (ar[a] + 1)
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("src/main/resources/input/puzzle5/input.txt")
    val puzzle = Puzzle5(input)
    val resultA = puzzle.processA()

    val max = resultA.maxByOrNull { it.id }!!
    println(max.id)

    val allIds = resultA.map { it.id }.sorted()
    val findMissing = findMissing(allIds, allIds.size)
    println(findMissing)
}