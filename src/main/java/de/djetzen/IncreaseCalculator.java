package de.djetzen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IncreaseCalculator {
    public int calculateFirstPart(String fileName) throws IOException {
        List<Integer> allLines = getAllLinesAsInteger(fileName);

        return calculateIncreases(allLines);
    }

    public int calculateSecondPart(String fileName) throws IOException {
        List<Integer> allLines = getAllLinesAsInteger(fileName);
        List<Integer> tripleSums = createTriples(allLines).stream().map(Triple::getSum).toList();

        return calculateIncreases(tripleSums);
    }

    private List<Integer> getAllLinesAsInteger(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream().map(Integer::parseInt).toList();
    }

    private int calculateIncreases(List<Integer> tripleSums) {
        var previousValue = tripleSums.get(0);
        var increases = 0;
        for (var tripleSum : tripleSums) {
            if (tripleSum > previousValue) {
                increases++;
            }
            previousValue = tripleSum;
        }
        return increases;
    }


    private List<Triple> createTriples(List<Integer> readLines) {
        var triples = new ArrayList<Triple>();
        for (int i = 0; i < readLines.size() - 2; i++) {
            triples.add(new Triple(readLines.get(i), readLines.get(i + 1), readLines.get(i + 2)));
        }
        return triples;
    }
}
