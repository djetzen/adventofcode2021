package de.djetzen;

import java.awt.*;
import java.io.IOException;

public class PositionCalculator {
    public Point calculatePosition(String fileName) throws IOException {
        var allLinesAsDirection = FileReader.getAllLinesAsSimpleDirection(fileName);
        var point = new Point();
        for (var line : allLinesAsDirection) {
            point = new Point(
                    getXValue((int) point.getX(), line),
                    getYValue((int) point.getY(), line));
        }

        return point;
    }


    private Integer getXValue(Integer previousX, Tuple tuple) {
        if (tuple.getDirection() == Direction.FORWARD) {
            return previousX + tuple.getValue();
        }
        return previousX;
    }

    private Integer getYValue(Integer previousY, Tuple tuple) {
        if (tuple.getDirection() == Direction.DOWN) {
            return previousY + tuple.getValue();
        }
        if (tuple.getDirection() == Direction.UP) {
            return previousY - tuple.getValue();
        }
        return previousY;
    }
}
