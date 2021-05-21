package com.peteriskandar.adventofcode2020.puzzle2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Puzzle2Test {

    lateinit var puzzle2: Puzzle2

    @BeforeEach
    fun setUp() {
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        puzzle2 = Puzzle2(input)
    }

    @Test
    fun `should get number of valid passwords`() {

        val result = puzzle2.processA()

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `should get the number of valid passwords part 2`() {

        val result = puzzle2.processB()
        assertThat(result).isEqualTo(1)
    }
}