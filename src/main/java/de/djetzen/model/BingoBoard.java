package de.djetzen.model;

import java.util.Arrays;
import java.util.List;

public class BingoBoard {
    private final BingoNumber[][] bingoNumbers;
    private int lastDrawnNumber;

    public BingoBoard(List<String> bingoLines) {
        var collectedNumbers = bingoLines.stream()
                .map(l -> l.trim().split("\\s+"))
                .map(this::getNumbersFromArray).toList();
        this.bingoNumbers = new BingoNumber[bingoLines.size()][bingoLines.size()];
        int i = 0;
        for (BingoNumber[] bingoLine : collectedNumbers) {
            bingoNumbers[i] = bingoLine;
            i++;
        }
    }

    public void drawNumber(int number) {
        for (int i = 0; i < bingoNumbers[0].length; i++) {
            for (int j = 0; j < bingoNumbers.length; j++) {
                if (bingoNumbers[i][j].getNumber() == number) {
                    bingoNumbers[i][j].markAsDrawn();
                }
            }
        }
        lastDrawnNumber = number;
    }

    public boolean isWon() {
        return isDiagonalLineWon() ||
                isAnyHorizontalLineWon() ||
                isAnyVerticalLineWon();
    }

    public int getFinalScore() {
        var sum = Arrays.stream(bingoNumbers)
                .flatMap(Arrays::stream)
                .filter(n -> !n.isDrawn())
                .map(BingoNumber::getNumber).mapToInt(Integer::intValue).sum();
        return sum * lastDrawnNumber;
    }

    private boolean isAnyVerticalLineWon() {
        for (int i = 0; i < bingoNumbers[0].length; i++) {
            var lineIsDrawn = areAllNumbersDrawn(bingoNumbers[0][i], bingoNumbers[1][i], bingoNumbers[2][i], bingoNumbers[3][i], bingoNumbers[4][i]);
            if (lineIsDrawn) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyHorizontalLineWon() {
        for (BingoNumber[] bingoNumber : bingoNumbers) {
            var lineIsDrawn = areAllNumbersDrawn(bingoNumber[0], bingoNumber[1], bingoNumber[2], bingoNumber[3], bingoNumber[4]);
            if (lineIsDrawn) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalLineWon() {
        return areAllNumbersDrawn(bingoNumbers[0][0],
                bingoNumbers[1][1],
                bingoNumbers[2][2],
                bingoNumbers[3][3],
                bingoNumbers[4][4]);
    }

    private boolean areAllNumbersDrawn(BingoNumber one, BingoNumber two, BingoNumber three, BingoNumber four, BingoNumber five) {
        return one.isDrawn() &&
                two.isDrawn() &&
                three.isDrawn() &&
                four.isDrawn() &&
                five.isDrawn();
    }

    private BingoNumber[] getNumbersFromArray(String[] numbers) {
        var bingonumbersFromParsedLine = new BingoNumber[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            bingonumbersFromParsedLine[i] = new BingoNumber(Integer.parseInt(numbers[i]));
        }
        return bingonumbersFromParsedLine;
    }
}
