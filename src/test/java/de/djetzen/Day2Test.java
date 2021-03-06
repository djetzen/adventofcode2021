package de.djetzen;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void the_example_for_the_first_part() throws IOException {
        var positionCalculator = new PositionCalculator();

        Point point = positionCalculator.calculateSimplePosition("src/test/resources/example/day2.txt");

        double solution = point.getX() * point.getY();

        assertThat(point.getX()).isEqualTo(15);
        assertThat(point.getY()).isEqualTo(10);
        assertThat(solution).isEqualTo(150);
    }

    @Test
    void the_real_input_for_the_first_part() throws IOException {
        var positionCalculator = new PositionCalculator();

        Point point = positionCalculator.calculateSimplePosition("src/test/resources/real/day2.txt");

        double solution = point.getX() * point.getY();

        assertThat(point.getX()).isEqualTo(1965);
        assertThat(point.getY()).isEqualTo(1182);
        assertThat(solution).isEqualTo(2322630);
    }

    @Test
    void the_example_for_the_second_part() throws IOException {
        var positionCalculator = new PositionCalculator();

        Point point = positionCalculator.calculatePositionWithAim("src/test/resources/example/day2.txt");

        double solution = point.getX() * point.getY();

        assertThat(point.getX()).isEqualTo(15);
        assertThat(point.getY()).isEqualTo(60);
        assertThat(solution).isEqualTo(900);
    }

    @Test
    void the_real_input_for_the_second_part() throws IOException {
        var positionCalculator = new PositionCalculator();

        Point point = positionCalculator.calculatePositionWithAim("src/test/resources/real/day2.txt");

        double solution = point.getX() * point.getY();

        assertThat(point.getX()).isEqualTo(1965);
        assertThat(point.getY()).isEqualTo(1071386);
        //double
        assertThat(solution).isEqualTo(2105273490);
    }


}
