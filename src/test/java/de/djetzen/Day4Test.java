package de.djetzen;

import de.djetzen.model.BingoBoard;
import de.djetzen.model.BingoNumber;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    @Test
    void the_example_input_for_first_part() throws IOException {
        var bingoInput = FileReader.getBingoInput("src/test/resources/example/day4.txt").trim();
        var bingoBoardLines = FileReader.getBingoBoards("src/test/resources/example/day4.txt");

        BingoBoard wonBoard = createBingoBoardsAndFindFirstWinningBoard(bingoInput, bingoBoardLines);

        assertThat(wonBoard.getFinalScore()).isEqualTo(4512);

    }


    @Test
    void the_real_input_for_first_part() throws IOException {
        var bingoInput = FileReader.getBingoInput("src/test/resources/real/day4.txt").trim();
        var bingoBoardLines = FileReader.getBingoBoards("src/test/resources/real/day4.txt");

        BingoBoard wonBoard = createBingoBoardsAndFindFirstWinningBoard(bingoInput, bingoBoardLines);

        assertThat(wonBoard.getFinalScore()).isEqualTo(6592);
    }

    @Test
    void figure_out_which_board_wins_last() throws IOException {
        var bingoInput = FileReader.getBingoInput("src/test/resources/real/day4.txt").trim();
        var bingoBoardLines = FileReader.getBingoBoards("src/test/resources/real/day4.txt");

        BingoBoard wonBoard = createBingoBoardsAndFindLastWinningBoard(bingoInput, bingoBoardLines);

        assertThat(wonBoard.getFinalScore()).isEqualTo(4512);
    }

    @NotNull
    private BingoBoard createBingoBoardsAndFindFirstWinningBoard(String bingoInput, List<List<String>> bingoBoardLines) {
        var bingoBoards = new ArrayList<BingoBoard>();
        for (List<String> bingoBoardLine : bingoBoardLines) {
            bingoBoards.add(new BingoBoard(bingoBoardLine));
        }

        for (String input : bingoInput.split(",")) {
            bingoBoards.forEach(b -> b.drawNumber(Integer.parseInt(input)));
            if (bingoBoards.stream().anyMatch(BingoBoard::isWon)) {
                break;
            }
        }
        var wonBoard = bingoBoards.stream().filter(BingoBoard::isWon).findFirst().get();
        return wonBoard;
    }

    private BingoBoard createBingoBoardsAndFindLastWinningBoard(String bingoInput, List<List<String>> bingoBoardLines) {
        var bingoBoards = new ArrayList<BingoBoard>();
        for (List<String> bingoBoardLine : bingoBoardLines) {
            bingoBoards.add(new BingoBoard(bingoBoardLine));
        }

        BingoBoard lastWinningBoard = null;

        for (String input : bingoInput.split(",")) {
            bingoBoards.forEach(b -> b.drawNumber(Integer.parseInt(input)));
            if (bingoBoards.stream().filter(BingoBoard::isWon).count() == bingoBoards.size() - 1) {
                lastWinningBoard = bingoBoards.stream().filter(b -> !b.isWon()).findFirst().get();
            }
            if (bingoBoards.stream().allMatch(BingoBoard::isWon)) {
                return lastWinningBoard;
            }
        }
        return null;
    }

    private BingoNumber[] createBingoRow(int first, int second, int third, int fourth, int fifth) {
        return new BingoNumber[]{new BingoNumber(first), new BingoNumber(second), new BingoNumber(third), new BingoNumber(fourth), new BingoNumber(fifth)};
    }
}
