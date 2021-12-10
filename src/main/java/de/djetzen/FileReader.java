package de.djetzen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    public static List<Integer> getAllLinesAsInteger(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream().map(Integer::parseInt).toList();
    }

    public static List<Tuple> getAllLinesAsSimpleDirection(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream().map(s -> s.split(" "))
                .map(s -> new Tuple(getDirection(s[0]), s[1]))
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
