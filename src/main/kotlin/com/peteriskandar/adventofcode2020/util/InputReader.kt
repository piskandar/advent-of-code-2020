package com.peteriskandar.adventofcode2020.util

import java.nio.file.Files
import java.nio.file.Paths

class InputReader {

    fun readInput(filePath: String): List<String> {
        val path = Paths.get(filePath)
        if(Files.exists(path)){
            return Files.readAllLines(path)
        }
        return emptyList()
    }
}