package com.peteriskandar.adventofcode2020.util

interface Puzzle<T> {
    fun processA(): T
    fun processB(): T
}