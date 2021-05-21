package com.peteriskandar.adventofcode2020.puzzle2

import com.peteriskandar.adventofcode2020.puzzle1.Puzzle1
import com.peteriskandar.adventofcode2020.util.InputReader
import com.peteriskandar.adventofcode2020.util.Puzzle
import java.lang.IllegalArgumentException

class Puzzle2(var input: List<String>) : Puzzle<Int> {

    override fun processA(): Int {
        var count = 0

        for (line in input) {
            println(line)
            val regex = Regex("^([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)")
            val matchResult = regex.find(line) ?: throw IllegalArgumentException("$line doesn't match regex")

            val (low, high, letter, password) = matchResult.destructured

            if(determineMatch(low.toInt(), high.toInt(), letter[0], password))
                count++
        }

        return count
    }

    private fun determineMatch(low: Int, high: Int, letter: Char, password: String): Boolean {
        val letterCount = password.count { it == letter }
        if(letterCount in low..high)
            return true

        return false
    }



    override fun processB(): Int {
        var count = 0

        for (line in input) {
            println(line)
            val regex = Regex("^([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)")
            val matchResult = regex.find(line) ?: throw IllegalArgumentException("$line doesn't match regex")

            val (first, second, letter, password) = matchResult.destructured

            if(isValid(first.toInt(), second.toInt(), letter[0], password))
                count++
        }

        return count
    }

    private fun isValid(first: Int, second: Int, letter: Char, password: String): Boolean {
        var isValid = false
        if(password[first-1] == letter){
            isValid = true
            if(password[second-1] == letter){
                isValid = false
            }
        }else if(password[second-1] == letter){
            isValid = true
        }

        return isValid
    }
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("src/main/resources/input/puzzle2/puzzle2.txt")
    val puzzle2 = Puzzle2(input)
    val count = puzzle2.processA()

    println(count)
    println(puzzle2.processB())
}