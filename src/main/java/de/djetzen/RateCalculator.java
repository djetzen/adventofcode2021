package de.djetzen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RateCalculator {
    public double calculateGammaRate(String fileName) throws IOException {
        var allLinesVertically = getAllLinesVertically(fileName);
        var concatenatedString = new StringBuilder();
        allLinesVertically.forEach(l -> concatenatedString.append(getMostUsedBitInColumn(l)));
        return Integer.parseInt(concatenatedString.toString(), 2);

    }

    public double calculateEpsilonRate(String fileName) throws IOException {
        List<List<Integer>> allLinesVertically = getAllLinesVertically(fileName);
        var concatenatedString = new StringBuilder();
        allLinesVertically.forEach(l -> concatenatedString.append(getLeastUsedBitInColumn(l)));
        return Integer.parseInt(concatenatedString.toString(), 2);
    }

    private List<List<Integer>> getAllLinesVertically(String fileName) throws IOException {
        var allLines = Files.readAllLines(Paths.get(fileName));
        var allLinesVertically = new ArrayList<List<Integer>>();

        IntStream.range(0, allLines.get(0).length())
                .forEach(i -> {
                            var valuesInColumn = allLines.stream().map(line -> Character.getNumericValue(line.charAt(i))).toList();
                            allLinesVertically.add(valuesInColumn);
                        }
                );
        return allLinesVertically;
    }

    private Integer getMostUsedBitInColumn(List<Integer> lineVertically) {
        long zeros = getCountOfNumberInList(lineVertically, 0);
        long ones = getCountOfNumberInList(lineVertically, 1);
        if (ones > zeros) {
            return 1;
        } else {
            return 0;
        }
    }

    private Integer getLeastUsedBitInColumn(List<Integer> lineVertically) {
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
