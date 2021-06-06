package com.peteriskandar.adventofcode2020.puzzle4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Puzzle4Test {

    lateinit var puzzle4: Puzzle4

    @BeforeEach
    fun setUp() {
        val input = """
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm

                iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                hcl:#cfa07d byr:1929

                hcl:#ae17e1 iyr:2013
                eyr:2024
                ecl:brn pid:760753108 byr:1931
                hgt:179cm

                hcl:#cfa07d eyr:2025 pid:166559648
                iyr:2011 ecl:brn hgt:59in
            """
        puzzle4 = Puzzle4(input)
    }

    @Test
    fun `should return number of valid passports`() {
        val result = puzzle4.processA()
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `should validate stricter`() {
        val invalidInput = """
            eyr:1972 cid:100
            hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
            
            iyr:2019
            hcl:#602927 eyr:1967 hgt:170cm
            ecl:grn pid:012533040 byr:1946
            
            hcl:dab227 iyr:2012
            ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
            
            hgt:59cm ecl:zzz
            eyr:2038 hcl:74454a iyr:2023
            pid:3556412378 byr:2007
        """

        val puzzle = Puzzle4(invalidInput)
        val count = puzzle.processB()
        assertThat(count).isEqualTo(0)

        val validInput = """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f

            eyr:2029 ecl:blu cid:129 byr:1989
            iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

            hcl:#888785
            hgt:164cm byr:2001 iyr:2015 cid:88
            pid:545766238 ecl:hzl
            eyr:2022

            iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
        """

        val validPuzzle = Puzzle4(validInput)
        val validCount = validPuzzle.processB()
        assertThat(validCount).isEqualTo(4)
    }

    @Nested
    internal class validator {

        lateinit var validator: Puzzle4.Validator
        @BeforeEach
        fun setUp() {
            validator = Puzzle4.Validator()
        }

        @Test
        fun `should validate year`() {
            assertThat(validator.checkYear("2002", 1920, 2002)).isTrue()
            assertThat(validator.checkYear("2003", 1920, 2002)).isFalse()
            assertThat(validator.checkYear("1920", 1920, 2002)).isTrue()
            assertThat(validator.checkYear("1919", 1920, 2002)).isFalse()
            assertThat(validator.checkYear("abcf", 1920, 2002)).isFalse()

        }

        @Test
        fun `should validate height`() {
            assertThat(validator.checkHeight("60in")).isTrue
            assertThat(validator.checkHeight("190cm")).isTrue
            assertThat(validator.checkHeight("190in")).isFalse()
            assertThat(validator.checkHeight("190")).isFalse()
        }

        @Test
        fun `should validate eye colour`() {
            assertThat(validator.checkEyeColor("amb")).isTrue()
            assertThat(validator.checkEyeColor("what")).isFalse()
        }

        @Test
        fun `should validate hairColor`() {
            assertThat(validator.checkHairColor("#123abc")).isTrue()
            assertThat(validator.checkHairColor("#123abz")).isFalse()
            assertThat(validator.checkHairColor("123abc")).isFalse()
        }

        @Test
        fun `should validate passport`() {
            assertThat(validator.checkPassport("000000001")).isTrue()
            assertThat(validator.checkPassport("1000000011")).isFalse()
        }
    }
}