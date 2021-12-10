package de.djetzen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IncreaseCalculator {
    public int calculate(String fileName) throws IOException {
        var allLines = Files.readAllLines(Paths.get(fileName)).stream().map(Integer::parseInt).toList();

        var previousValue = allLines.get(0);
        var increases = 0;
        for (var line : allLines) {
            if (line > previousValue) {
                increases++;
            }
            previousValue = line;
        }
        return increases;
    }
}
