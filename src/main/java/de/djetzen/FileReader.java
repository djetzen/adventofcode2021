package de.djetzen;

import de.djetzen.model.Direction;
import de.djetzen.model.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileReader {
    public static List<Integer> getAllLinesAsInteger(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream().map(Integer::parseInt).toList();
    }

    public static List<List<Integer>> getAllLinesVertically(String fileName) throws IOException {
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

    public static List<Tuple> getAllLinesAsSimpleDirection(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream().map(s -> s.split(" "))
                .map(s -> new Tuple(getDirection(s[0]), Integer.parseInt(s[1])))
                .toList();
    }

    public static String getBingoInput(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).get(0);
    }

    public static List<List<String>> getBingoBoards(String fileName) throws IOException {
        var allLinesRead = Files.readAllLines(Paths.get(fileName));
        var onlyFilledLines = allLinesRead.subList(1, allLinesRead.size()).stream().filter(s -> !s.isBlank()).collect(Collectors.toList());

        var subSets =
                IntStream.range(0, (onlyFilledLines.size() / 5))
                        .mapToObj(i -> onlyFilledLines.subList(5 * i, (5 * i) + 5))
                        .collect(Collectors.toList());

        var boards = new ArrayList<List<String>>(subSets);
        return boards;
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
