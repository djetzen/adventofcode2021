package de.djetzen;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void the_example_input_for_first_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.getSumOfDepthIncreases("src/test/resources/example/day1.txt");

        assertThat(solution).isEqualTo(7);
    }


    @Test
    void the_real_input_for_first_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.getSumOfDepthIncreases("src/test/resources/real/day1.txt");

        assertThat(solution).isEqualTo(1602);
    }

    @Test
    void the_example_input_for_second_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.getSumOfDepthIncreasesForThreeConsecutiveMeasurements("src/test/resources/example/day1.txt");

        assertThat(solution).isEqualTo(5);
    }


    @Test
    void the_real_input_for_second_part() throws IOException {
        var increaseCalculator = new IncreaseCalculator();

        var solution = increaseCalculator.getSumOfDepthIncreasesForThreeConsecutiveMeasurements("src/test/resources/real/day1.txt");

        assertThat(solution).isEqualTo(1633);
    }
}
