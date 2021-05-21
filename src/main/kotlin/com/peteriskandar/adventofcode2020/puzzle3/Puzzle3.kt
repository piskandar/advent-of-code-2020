package com.peteriskandar.adventofcode2020.puzzle3

import com.peteriskandar.adventofcode2020.puzzle1.Puzzle1
import com.peteriskandar.adventofcode2020.util.InputReader
import com.peteriskandar.adventofcode2020.util.Puzzle

class Puzzle3(private var input: List<String>) : Puzzle<Long> {
    override fun processA(): Long {
        return process(input, 3, 1)
    }

    private fun process(input: List<String>, across: Int, down:Int): Long {
        val arrayGrid = createGrid(input)

        val rowCount = arrayGrid.size
        val colCount = arrayGrid[0].size

        val startingPosition = Pair(0,0)
        var currentPosition = startingPosition.copy()
        var treeCount:Long = 0
        var currentRow = 0

        while(currentRow < rowCount){

            var newCol = currentPosition.second + across
            if(newCol > colCount - 1){
                newCol = newCol % (colCount - 1) - 1
            }

            currentPosition = Pair(currentPosition.first + down, newCol)
            if(currentPosition.first > rowCount - 1)
                break

            val current = arrayGrid[currentPosition.first][currentPosition.second]

            currentRow++
            if(current == '#'){
                treeCount++
            }
        }

        return treeCount
    }

    private fun createGrid(input: List<String>): Array<CharArray> {
        return Array(input.size) {
            input[it].toCharArray()
        }
    }

    override fun processB(): Long {

        val list = mutableListOf<Long>()
        list.add(process(input, 1, 1))
        list.add(process(input, 3, 1))
        list.add(process(input, 5, 1))
        list.add(process(input, 7, 1))
        list.add(process(input, 1, 2))

        var product:Long = 1
        list.forEach {
            product *= it
        }
        return product
    }

}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("src/main/resources/input/puzzle3/input.txt")
    val puzzle = Puzzle3(input)
    val resultA = puzzle.processA()
    println(resultA)

    val resultB = puzzle.processB()
    println(resultB)
}