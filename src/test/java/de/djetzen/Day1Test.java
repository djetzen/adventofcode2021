package de.djetzen;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void the_example_input_for_first_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.calculate("src/test/resources/example/day1_1.txt");

        assertThat(solution).isEqualTo(7);
    }


    @Test
    void the_real_input_for_first_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.calculate("src/test/resources/real/day1_1.txt");

        assertThat(solution).isEqualTo(1602);
    }
}
