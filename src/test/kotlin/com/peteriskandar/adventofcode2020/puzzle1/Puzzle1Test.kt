package com.peteriskandar.adventofcode2020.puzzle1

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

class Puzzle1Test {

    lateinit var puzzle1: Puzzle1
    @BeforeEach
    fun setUp() {
        val input = listOf(
            "1721",
            "979",
            "366",
            "299",
            "675",
            "1456",
        )
        puzzle1 = Puzzle1(input)
    }

    @Test
    fun `should find two that sum to 2020`() {

        val result = puzzle1.processA()

        assertThat(result).isEqualTo(514579)

    }

    @Test
    fun `should find three that sum to 2020`() {

        val result = puzzle1.processB()

        assertThat(result).isEqualTo(241861950)
    }
}