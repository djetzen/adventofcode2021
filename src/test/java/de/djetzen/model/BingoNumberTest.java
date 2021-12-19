package de.djetzen.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BingoNumberTest {

    @Test
    void initially_the_number_is_not_drawn() {
        var number = new BingoNumber(5);

        assertThat(number.isDrawn()).isFalse();
    }


    @Test
    void the_number_can_be_marked_as_drawn() {
        var number = new BingoNumber(5);
        number.markAsDrawn();

        assertThat(number.isDrawn()).isTrue();
    }
}