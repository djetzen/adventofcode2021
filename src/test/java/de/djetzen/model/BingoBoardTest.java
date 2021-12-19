package de.djetzen.model;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BingoBoardTest {

    @Test
    void a_bingo_board_is_initially_not_won() {
        var bingoNumbers = getSimpleBingoBoard();
        var bingoBoard = new BingoBoard(bingoNumbers);

        assertThat(bingoBoard.isWon()).isFalse();
    }

    @Test
    void a_bingo_board_with_a_vertical_line_drawn_is_won() {
        var bingoNumbers = getSimpleBingoBoard();
        var bingoBoard = new BingoBoard(bingoNumbers);

        bingoBoard.drawNumber(1);
        bingoBoard.drawNumber(6);
        bingoBoard.drawNumber(11);
        bingoBoard.drawNumber(16);
        bingoBoard.drawNumber(21);

        assertThat(bingoBoard.isWon()).isTrue();
    }


    @Test
    void a_bingo_board_with_a_horizontal_line_drawn_is_won() {
        var bingoNumbers = getSimpleBingoBoard();
        var bingoBoard = new BingoBoard(bingoNumbers);

        bingoBoard.drawNumber(1);
        bingoBoard.drawNumber(2);
        bingoBoard.drawNumber(3);
        bingoBoard.drawNumber(4);
        bingoBoard.drawNumber(5);

        assertThat(bingoBoard.isWon()).isTrue();
    }


    @Test
    void a_bingo_board_with_a_diagonal_line_drawn_is_won() {
        var bingoNumbers = getSimpleBingoBoard();
        var bingoBoard = new BingoBoard(bingoNumbers);

        bingoBoard.drawNumber(1);
        bingoBoard.drawNumber(7);
        bingoBoard.drawNumber(13);
        bingoBoard.drawNumber(19);
        bingoBoard.drawNumber(25);

        assertThat(bingoBoard.isWon()).isTrue();
    }

    @Test
    void the_final_score_are_the_unmarked_numbers_multiplied_with_last_drawn_number() {
        var bingoNumbers = getSimpleBingoBoard();
        var bingoBoard = new BingoBoard(bingoNumbers);

        bingoBoard.drawNumber(1);
        bingoBoard.drawNumber(2);
        bingoBoard.drawNumber(3);
        bingoBoard.drawNumber(4);
        bingoBoard.drawNumber(5);

        assertThat(bingoBoard.isWon()).isTrue();
        assertThat(bingoBoard.getFinalScore()).isEqualTo(310 * 5);
    }

    @NotNull
    private List<String> getSimpleBingoBoard() {
        return Arrays.asList(createBingoRow(1, 2, 3, 4, 5),
                createBingoRow(6, 7, 8, 9, 10),
                createBingoRow(11, 12, 13, 14, 15),
                createBingoRow(16, 17, 18, 19, 20),
                createBingoRow(21, 22, 23, 24, 25));
    }

    private String createBingoRow(int first, int second, int third, int fourth, int fifth) {
        return first + " " + second + " " + third + " " + fourth + " " + fifth;
    }

}