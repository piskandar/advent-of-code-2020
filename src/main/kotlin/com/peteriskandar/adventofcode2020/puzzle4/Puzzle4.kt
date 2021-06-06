package com.peteriskandar.adventofcode2020.puzzle4

import com.peteriskandar.adventofcode2020.puzzle3.Puzzle3
import com.peteriskandar.adventofcode2020.util.InputReader
import com.peteriskandar.adventofcode2020.util.Puzzle
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.regex.Pattern

class Puzzle4(private val input: String) : Puzzle<Long> {
    private val keys = listOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid",
        "cid",
    )
    private val validator = Validator()

    override fun processA(): Long {

        val passports = processInput()
        var validPassportCount = 0L

        for(passport in passports) {
            if(validatePassport(passport)){
                validPassportCount++
            }
        }

        return validPassportCount
    }

    private fun validatePassport(passport: Map<String, String>): Boolean {

        var valid = true

        if(!passport.containsKey("byr")) valid = valid && false
        if(!passport.containsKey("iyr")) valid = valid && false
        if(!passport.containsKey("eyr")) valid = valid && false
        if(!passport.containsKey("hgt")) valid = valid && false
        if(!passport.containsKey("hcl")) valid = valid && false
        if(!passport.containsKey("ecl")) valid = valid && false
        if(!passport.containsKey("pid")) valid = valid && false

        return valid
    }

    private fun validatePassport2(passport: Map<String, String>): Boolean {

        var valid = validatePassport(passport)
        if(!valid){
            return valid
        }

        if(passport.containsKey("byr")) valid = valid && validator.checkYear(passport["byr"]!!,1920, 2002)
        if(passport.containsKey("iyr")) valid = valid && validator.checkYear(passport["iyr"]!!,2010, 2020)
        if(passport.containsKey("eyr")) valid = valid && validator.checkYear(passport["eyr"]!!,2020, 2030)
        if(passport.containsKey("hgt")) valid = valid && validator.checkHeight(passport["hgt"]!!)
        if(passport.containsKey("hcl")) valid = valid && validator.checkHairColor(passport["hcl"]!!)
        if(passport.containsKey("ecl")) valid = valid && validator.checkEyeColor(passport["ecl"]!!)
        if(passport.containsKey("pid")) valid = valid && validator.checkPassport(passport["pid"]!!)

        return valid
    }

    private fun processInput(): List<Map<String, String>> {
        val passports = input.split(Pattern.compile("\n\n"))
        val regex = Regex("([a-z]{3}):([0-9a-zA-Z#]+)")
        val list = mutableListOf<Map<String, String>>()
        for (passport in passports) {
            val map = mutableMapOf<String, String>()
            val matchResult = regex.findAll(passport) ?: throw IllegalArgumentException("$passport doesn't match regex")
            for (result in matchResult) {
                if (result.groups.size == 3) {
                    val key = result.groupValues[1]
                    val value = result.groupValues[2]
                    map[key] = value
                } else {
                    throw IllegalStateException("more than 3 groups")
                }
            }

            list.add(map)
        }
        return list
    }

    override fun processB(): Long {
        val passports = processInput()
        var validPassportCount = 0L
        for(passport in passports) {
            if(validatePassport2(passport)){
                validPassportCount++
            }
        }

        return validPassportCount
    }




    class Validator {
        private val yearRegex = Regex("[0-9][0-9][0-9][0-9]")
        private val heightRegex = Regex("([0-9]+)(cm|in)")
        private val hairColorRegex = Regex("#[0-9a-f]{6}")
        private val passportRegex = Regex("[0-9]{9}")
        private val eyeColors = listOf(
            "amb",
            "blu",
            "brn",
            "gry",
            "grn",
            "hzl",
            "oth"
        )

        fun checkPassport(value: String): Boolean {
            return passportRegex.matches(value)
        }

        fun checkEyeColor(value: String): Boolean {
            return eyeColors.contains(value)
        }

        fun checkHairColor(value: String): Boolean {
            return hairColorRegex.matches(value)
        }

        fun checkYear(value: String, start: Int, end: Int): Boolean {
            if(yearRegex.matches(value))
            {
                val num = value.toInt()
                if(num < start || num > end){
                    return false
                }
                return true
            }
            return false
        }

        fun checkHeight(value: String): Boolean {
            val matchEntire = heightRegex.matchEntire(value) ?: return false
            val (height, unit) = matchEntire.destructured

            val heightValue = height.toInt()
            if(unit == "in"){
                if(heightValue < 59 || heightValue > 76){
                    return false
                }
            }else if (unit == "cm"){
                if(heightValue < 150 || heightValue > 193){
                    return false
                }
            }
            return true
        }


    }

}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readFullInput("src/main/resources/input/puzzle4/input.txt")
    if(input != null) {
        val puzzle = Puzzle4(input)
        val resultA = puzzle.processA()
        println(resultA)

        val resultB = puzzle.processB()
        println(resultB)
    }
}