package de.djetzen;

import java.awt.*;
import java.io.IOException;

public class PositionCalculator {

    public Point calculateSimplePosition(String fileName) throws IOException {
        var allLinesAsDirection = FileReader.getAllLinesAsSimpleDirection(fileName);
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
        var allLinesAsDirection = FileReader.getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var tuple : allLinesAsDirection) {
            point = new Point(
                    getXValueWithAim(point.x, tuple),
                    getYValueWithAim(point.y, tuple, aim));

            aim = updateAim(tuple, aim);
        }

        return point;
    }

    private Integer getXValueWithAim(Integer previousX, Tuple tuple) {
        return getSimpleXValue(previousX, tuple);
    }

    private Integer getYValueWithAim(Integer previousY, Tuple tuple, int aim) {
        if (tuple.direction() == Direction.FORWARD) {
            return previousY + (tuple.value() * aim);
        }
        return previousY;
    }

    private Integer updateAim(Tuple tuple, int aim) {
        if (tuple.direction() == Direction.DOWN) {
            return aim + tuple.value();
        }
        if (tuple.direction() == Direction.UP) {
            return aim - tuple.value();
        }
        return aim;
    }


    private Integer getSimpleXValue(Integer previousX, Tuple tuple) {
        if (tuple.direction() == Direction.FORWARD) {
            return previousX + tuple.value();
        }
        return previousX;
    }

    private Integer getSimpleYValue(Integer previousY, Tuple tuple) {
        if (tuple.direction() == Direction.DOWN) {
            return previousY + tuple.value();
        }
        if (tuple.direction() == Direction.UP) {
            return previousY - tuple.value();
        }
        return previousY;
    }
}
