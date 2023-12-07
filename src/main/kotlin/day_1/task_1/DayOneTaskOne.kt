package day_1.task_1

import java.io.BufferedReader
import java.io.File

class DayOneTaskOne {
    fun readFile(name: String) : String {
        val bufferedReader: BufferedReader = File(name).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun calculateTaskOne(text: String) : Int {
        val lines = text.split("\n")

        return lines.sumOf { line -> getFirstAndLastDigit(line) }
    }

    fun getFirstAndLastDigit(line: String) : Int {
        val digits = line.filter { it.isDigit() }
        val firstDigit = digits.get(0)
        val lastDigit = digits.get(digits.length - 1)
        val firstAndLastDigit = "$firstDigit$lastDigit"
        return firstAndLastDigit.toInt()
    }
}
fun main() {
    val task = DayOneTaskOne()
    val text = task.readFile("src/main/resources/day_1/task_1/input.txt")
    println(task.calculateTaskOne(text))
}