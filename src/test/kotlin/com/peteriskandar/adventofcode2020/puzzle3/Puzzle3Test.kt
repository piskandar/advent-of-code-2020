package com.peteriskandar.adventofcode2020.puzzle3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Puzzle3Test{

    lateinit var puzzle3: Puzzle3

    @BeforeEach
    fun setUp() {

        var input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#",
        )

        puzzle3 = Puzzle3(input)
    }

    @Test
    fun `should count the trees encountered`() {
        val result = puzzle3.processA()

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should test different slopes`() {
        val result = puzzle3.processB()

        assertThat(result).isEqualTo(336)

    }
}