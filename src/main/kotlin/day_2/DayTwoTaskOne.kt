package day_2

import java.io.BufferedReader
import java.io.File
import java.util.regex.Pattern
import java.util.stream.Collectors

class DayTwoTaskOne {
    val loadedCubes = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
    fun readFile(name: String): String {
        val bufferedReader: BufferedReader = File(name).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun playGame(input: String): Int {
        val lines = input.split("\n")
        return lines.sumOf { line -> getCountableValuePerLine(line) }
    }

    private fun getCountableValuePerLine(line: String): Int {
        val pattern = Pattern.compile("[:;,]")
        val splittedGame = line.split(pattern)
        return if (isGameValid(splittedGame.subList(1, splittedGame.size))) {
            splittedGame[0].split(" ")[1].toInt()
        } else {
            0
        }
    }

    private fun isGameValid(cubesPulled: List<String>): Boolean {
        for (cubesPulledForOneColor in cubesPulled) {

            for (loadedCubeColor in loadedCubes) {
                val splittedCubesPulledForOneColor = cubesPulledForOneColor.split(" ").stream().filter { split -> !split.isEmpty() }.collect(Collectors.toList())
                if (isPulledCubesPerColorInvalid(splittedCubesPulledForOneColor, loadedCubeColor)) {
                    return false
                }
            }

        }
        return true
    }

    private fun isPulledCubesPerColorInvalid(splittedCubesPulledForOneColor: List<String>, loadedCubeColor: Map.Entry<String, Int>)
    = splittedCubesPulledForOneColor[1].contains(loadedCubeColor.key) && splittedCubesPulledForOneColor[0].toInt() > loadedCubeColor.value
}

fun main() {
    val dayTwoTaskOne = DayTwoTaskOne()
    val input = dayTwoTaskOne.readFile("src/main/resources/day_2/input.txt")
    println("Result: " + dayTwoTaskOne.playGame(input))
}
