package de.djetzen;

import de.djetzen.model.Direction;
import de.djetzen.model.DirectionTuple;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PositionCalculator {

    public Point calculateSimplePosition(String fileName) throws IOException {
        var allLinesAsDirection = getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var line : allLinesAsDirection) {
            point = new Point(
                    getSimpleXValue(point.x, line),
                    getSimpleYValue(point.y, line));
        }

        return point;
    }

    public Point calculatePositionWithAim(String fileName) throws IOException {
        var aim = 0;
        var allLinesAsDirection = getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var tuple : allLinesAsDirection) {
            point = new Point(
                    getXValueWithAim(point.x, tuple),
                    getYValueWithAim(point.y, tuple, aim));

            aim = updateAim(tuple, aim);
        }

        return point;
    }

    private List<DirectionTuple> getAllLinesAsSimpleDirection(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream().map(s -> s.split(" "))
                .map(s -> new DirectionTuple(getDirection(s[0]), Integer.parseInt(s[1])))
                .toList();
    }

    private Direction getDirection(String s) {
        if (s.equals("forward")) {
            return Direction.FORWARD;
        }
        if (s.equals("up")) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

    private Integer getXValueWithAim(Integer previousX, DirectionTuple directionTuple) {
        return getSimpleXValue(previousX, directionTuple);
    }

    private Integer getYValueWithAim(Integer previousY, DirectionTuple directionTuple, int aim) {
        if (directionTuple.direction() == Direction.FORWARD) {
            return previousY + (directionTuple.value() * aim);
        }
        return previousY;
    }

    private Integer updateAim(DirectionTuple directionTuple, int aim) {
        if (directionTuple.direction() == Direction.DOWN) {
            return aim + directionTuple.value();
        }
        if (directionTuple.direction() == Direction.UP) {
            return aim - directionTuple.value();
        }
        return aim;
    }


    private Integer getSimpleXValue(Integer previousX, DirectionTuple directionTuple) {
        if (directionTuple.direction() == Direction.FORWARD) {
            return previousX + directionTuple.value();
        }
        return previousX;
    }

    private Integer getSimpleYValue(Integer previousY, DirectionTuple directionTuple) {
        if (directionTuple.direction() == Direction.DOWN) {
            return previousY + directionTuple.value();
        }
        if (directionTuple.direction() == Direction.UP) {
            return previousY - directionTuple.value();
        }
        return previousY;
    }
}
