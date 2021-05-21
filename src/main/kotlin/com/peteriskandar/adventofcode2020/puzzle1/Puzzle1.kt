package com.peteriskandar.adventofcode2020.puzzle1

import com.google.common.collect.Sets
import com.peteriskandar.adventofcode2020.util.InputReader
import com.peteriskandar.adventofcode2020.util.Puzzle

class Puzzle1(input: List<String>) : Puzzle<Int> {

    private var parsedInput: List<Int> = input.map { it.toInt() }

    override fun processA(): Int {
        return process(2)
    }

    private fun process(size: Int): Int {
        val  combinations = Sets.combinations(parsedInput.toMutableSet(), size)

        for (combination in combinations) {
            val combo = combination.toIntArray()

            if(combo.sum() == 2020) {
                var product = 1
                for(i in combo){
                    product *= i
                }
                return product
            }
        }

        return -1
    }


    override fun processB(): Int {
        return process(3)
    }
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("src/main/resources/input/puzzle1/puzzle1a.txt")
    val puzzle1 = Puzzle1(input)
    val result = puzzle1.processA()
    println(result)

    val result2 = puzzle1.processB()
    println(result2)
}