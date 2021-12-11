package de.djetzen;

import java.io.IOException;
import java.util.List;

public class RateCalculator {
    public double calculateGammaRate(String fileName) throws IOException {
        var allLinesVertically = FileReader.getAllLinesVertically(fileName);
        var concatenatedString = new StringBuilder();
        allLinesVertically.forEach(l -> concatenatedString.append(getLeadingNumbersInLines(l)));
        return Integer.parseInt(concatenatedString.toString(), 2);

    }

    public double calculateEpsilonRate(String fileName) throws IOException {
        List<List<Integer>> allLinesVertically = FileReader.getAllLinesVertically(fileName);
        var concatenatedString = new StringBuilder();
        allLinesVertically.forEach(l -> concatenatedString.append(getLosingNumbersInLines(l)));
        return Integer.parseInt(concatenatedString.toString(), 2);
    }

    private Integer getLeadingNumbersInLines(List<Integer> lineVertically) {
        long zeros = getCountOfNumberInList(lineVertically, 0);
        long ones = getCountOfNumberInList(lineVertically, 1);
        if (ones > zeros) {
            return 1;
        } else {
            return 0;
        }
    }

    private Integer getLosingNumbersInLines(List<Integer> lineVertically) {
        long zeros = getCountOfNumberInList(lineVertically, 0);
        long ones = getCountOfNumberInList(lineVertically, 1);
        if (ones < zeros) {
            return 1;
        } else {
            return 0;
        }
    }

    private long getCountOfNumberInList(List<Integer> lineVertically, int numberToSearch) {
        return lineVertically.stream().filter(n -> n == numberToSearch).count();
    }
}
