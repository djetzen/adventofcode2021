package de.djetzen;

import de.djetzen.model.Direction;
import de.djetzen.model.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FileReader {
    public static List<Integer> getAllLinesAsInteger(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream().map(Integer::parseInt).toList();
    }

    public static List<List<Integer>> getAllLinesVertically(String fileName) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(fileName));
        var allLinesVertically = new ArrayList<List<Integer>>();
        IntStream.range(0, allLines.get(0).length()).forEach(i -> allLinesVertically.add(new ArrayList<>()));
        allLines.forEach(line -> {
            for (int i = 0; i < line.length(); i++) {
                allLinesVertically.get(i).add(Character.getNumericValue(line.charAt(i)));
            }
        });

        return allLinesVertically;
    }

    public static List<Tuple> getAllLinesAsSimpleDirection(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream().map(s -> s.split(" "))
                .map(s -> new Tuple(getDirection(s[0]), Integer.parseInt(s[1])))
                .toList();
    }

    private static Direction getDirection(String s) {
        if (s.equals("forward")) {
            return Direction.FORWARD;
        }
        if (s.equals("up")) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

}
