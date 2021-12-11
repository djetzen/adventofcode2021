package de.djetzen.model;

public record Triple(int a, int b, int c) {


    public int getSum() {
        return a + b + c;
    }
}
