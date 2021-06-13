package com.peteriskandar.adventofcode2020.puzzle5

import com.peteriskandar.adventofcode2020.puzzle3.Puzzle3
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Puzzle5Test {

    @Test
    fun `should get boarding data`() {
        var puzzle = Puzzle5(listOf("FBFBBFFRLR"))
        var result = puzzle.processA()
        var expected = BoardingData(44, 5, 357)
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(expected)

        puzzle = Puzzle5(listOf("BFFFBBFRRR"))
        expected = BoardingData(70, 7, 567)
        result = puzzle.processA()
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(expected)

        puzzle = Puzzle5(listOf("FFFBBBFRRR"))
        expected = BoardingData(14, 7, 119)
        result = puzzle.processA()
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(expected)

        puzzle = Puzzle5(listOf("BBFFBBFRLL"))
        expected = BoardingData(102, 4, 820)
        result = puzzle.processA()
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(expected)
    }
}