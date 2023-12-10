package day_2

import java.io.BufferedReader
import java.io.File
import java.util.regex.Pattern
import java.util.stream.Collectors

class DayTwoTaskTwo {
    fun readFile(name: String): String {
        val bufferedReader: BufferedReader = File(name).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun playGame(input: String): Int {
        val games = input.split("\n")
        return games.sumOf { line -> getMultipliedValuePerGame(line) }
    }

    private fun getMultipliedValuePerGame(line: String): Int {
        val pulledCubes = extractPulledCubesPerGame(line)

        val biggestCountPerColor = getBiggestCountPerColor(pulledCubes)

        var multipliedValue = 1
        for (biggestCount in biggestCountPerColor) {
             multipliedValue *= biggestCount.value
        }

        return multipliedValue
    }

    private fun getBiggestCountPerColor(pulledCubesPerGame: List<String>) : Map<String, Int> {
        val biggestCountPerColor = mutableMapOf<String, Int>()

        for (pulledCubePerColor in pulledCubesPerGame) {
            val separatedPulledCubePerColor = separateColorAndCount(pulledCubePerColor)

            if (biggestCountPerColor.containsKey(separatedPulledCubePerColor[1])) {
                if (biggestCountPerColor[separatedPulledCubePerColor[1]]!! < separatedPulledCubePerColor[0].toInt()) {
                    biggestCountPerColor[separatedPulledCubePerColor[1]] = separatedPulledCubePerColor[0].toInt()
                }
            } else {
                biggestCountPerColor[separatedPulledCubePerColor[1]] = separatedPulledCubePerColor[0].toInt()
            }
        }

        return biggestCountPerColor
    }

    private fun separateColorAndCount(pulledCubePerColor: String): MutableList<String> =
        pulledCubePerColor.split(" ").stream().filter { element -> element.isNotEmpty() }
            .collect(Collectors.toList())

    private fun extractPulledCubesPerGame(line: String) : List<String> {
        val pattern = Pattern.compile("[:;,]")
        val splittedGame = line.split(pattern)
        return splittedGame.subList(1, splittedGame.size)
    }
}

fun main() {
    val dayTwoTaskTwo = DayTwoTaskTwo()
    val input = dayTwoTaskTwo.readFile("src/main/resources/day_2/input.txt")
    println("Result: " + dayTwoTaskTwo.playGame(input))
}
