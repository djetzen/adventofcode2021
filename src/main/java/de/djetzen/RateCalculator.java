package de.djetzen;

import java.io.IOException;
import java.util.List;

public class RateCalculator {
    public double calculateGammaRate(String fileName) throws IOException {
        var allLinesVertically = FileReader.getAllLinesVertically(fileName);
        var concatenatedString = "";
        for (var lineVertically : allLinesVertically) {
            concatenatedString += getLeadingNumbersInLines(lineVertically);
        }
        return Integer.parseInt(concatenatedString, 2);

    }

    public double calculateEpsilonRate(String fileName) throws IOException {
        List<List<Integer>> allLinesVertically = FileReader.getAllLinesVertically(fileName);
        var concatenatedString = "";
        for (var lineVertically : allLinesVertically) {
            concatenatedString += getLosingNumbersInLines(lineVertically);
        }
        return Integer.parseInt(concatenatedString, 2);
    }

    private Integer getLeadingNumbersInLines(List<Integer> lineVertically) {
        long zeros = getNumberInLine(lineVertically, 0);
        long ones = getNumberInLine(lineVertically, 1);
        if (ones > zeros) {
            return 1;
        } else {
            return 0;
        }
    }

    private Integer getLosingNumbersInLines(List<Integer> lineVertically) {
        long zeros = getNumberInLine(lineVertically, 0);
        long ones = getNumberInLine(lineVertically, 1);
        if (ones < zeros) {
            return 1;
        } else {
            return 0;
        }
    }

    private long getNumberInLine(List<Integer> lineVertically, int i) {
        return lineVertically.stream().filter(n -> n == i).count();
    }
}
