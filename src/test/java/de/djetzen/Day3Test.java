package de.djetzen;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    @Test
    void the_example_input_for_first_part() throws IOException {
        var fileName = "src/test/resources/example/day3.txt";
        var rateCalculator = new RateCalculator();

        double gammaRate = rateCalculator.calculateGammaRate(fileName);
        double epsilonRate = rateCalculator.calculateEpsilonRate(fileName);
        assertThat(gammaRate * epsilonRate).isEqualTo(198);
    }

    @Test
    void the_real_input_for_first_part() throws IOException {
        var fileName = "src/test/resources/real/day3.txt";
        var rateCalculator = new RateCalculator();

        double gammaRate = rateCalculator.calculateGammaRate(fileName);
        double epsilonRate = rateCalculator.calculateEpsilonRate(fileName);
        assertThat(gammaRate * epsilonRate).isEqualTo(1131506);
    }
}
