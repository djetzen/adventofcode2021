package de.djetzen;

public class Triple {

    private final int a;
    private final int b;
    private final int c;

    public Triple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getSum() {
        return a + b + c;
    }
}
