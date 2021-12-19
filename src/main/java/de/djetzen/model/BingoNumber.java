package de.djetzen.model;

public class BingoNumber {
    private int number;

    private boolean drawn = false;

    public BingoNumber(int number) {
        this.number = number;
    }

    public void markAsDrawn() {
        drawn = true;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public int getNumber() {
        return number;
    }
}
