package de.djetzen;

import java.awt.*;
import java.io.IOException;

public class PositionCalculator {

    public Point calculateSimplePosition(String fileName) throws IOException {
        var allLinesAsDirection = FileReader.getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var line : allLinesAsDirection) {
            point = new Point(
                    getSimpleXValue((int) point.getX(), line),
                    getSimpleYValue((int) point.getY(), line));
        }

        return point;
    }

    public Point calculatePositionWithAim(String fileName) throws IOException {
        var aim = 0;
        var allLinesAsDirection = FileReader.getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var line : allLinesAsDirection) {
            point = new Point(
                    getSimpleXValue((int) point.getX(), line),
                    getSimpleYValue((int) point.getY(), line));
        }

        return point;
    }

    private Integer getXValueWithAim() {
        return 0;
    }

    private Integer getYValueWithAim() {
        return 0;
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
