package de.djetzen;

public class Tuple {

    private Direction direction;
    private Integer value;

    public Tuple(Direction first, Integer second) {
        this.direction = first;
        this.value = second;
    }


    public Tuple(Direction first, String second) {
        this.direction = first;
        this.value = Integer.parseInt(second);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
