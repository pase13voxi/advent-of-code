package day_1

import java.io.BufferedReader
import java.io.File

class DayOneTaskOne {
    val conversionRules = mapOf(
        "one" to "on1e",
        "two" to "tw2o",
        "three" to "thr3ee",
        "four" to "fo4ur",
        "five" to "fi5ve",
        "six" to "si6x",
        "seven" to "se7ven",
        "eight" to "eig8ht",
        "nine" to "ni9ne"
    )

    fun readFile(name: String): String {
        val bufferedReader: BufferedReader = File(name).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun calculateTaskOne(text: String): Int {
        val lines = text.split("\n")
        val convertedLines = lines.map { line -> convertWordsToDigits(line) }
        convertedLines.forEach { println(it) }
        return convertedLines.sumOf { convertedLine -> getFirstAndLastDigit(convertedLine) }
    }

    fun convertWordsToDigits(line: String) : String {
        var convertedLine = ""
        line.forEach { c: Char ->
            convertedLine = "$convertedLine$c"

            conversionRules.forEach{ entry ->
                convertedLine = convertedLine.replace(entry.key, entry.value)
            }
        }
        return convertedLine
    }

    fun getFirstAndLastDigit(line: String): Int {
        val digits = line.filter { it.isDigit() }
        val firstDigit = digits.get(0)
        val lastDigit = digits.get(digits.length - 1)
        val firstAndLastDigit = "$firstDigit$lastDigit"
        return firstAndLastDigit.toInt()
    }
}

fun main() {
    val task = DayOneTaskOne()
    val text = task.readFile("src/main/resources/day_1/input.txt")
    println(task.calculateTaskOne(text))
}