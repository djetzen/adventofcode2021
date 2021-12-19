package de.djetzen;

import de.djetzen.model.BingoBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    @Test
    void the_example_input_for_first_part() throws IOException {
        BingoBoard wonBoard = createBingoBoardsAndFindFirstWinningBoard("src/test/resources/example/day4.txt");

        assert wonBoard != null;
        assertThat(wonBoard.getFinalScore()).isEqualTo(4512);

    }

    @Test
    void the_real_input_for_first_part() throws IOException {
        BingoBoard wonBoard = createBingoBoardsAndFindFirstWinningBoard("src/test/resources/real/day4.txt");

        assert wonBoard != null;
        assertThat(wonBoard.getFinalScore()).isEqualTo(6592);
    }

    @Test
    void figure_out_which_board_wins_last() throws IOException {
        BingoBoard lastWinningBoard = createBingoBoardsAndFindLastWinningBoard("src/test/resources/real/day4.txt");

        assert lastWinningBoard != null;
        assertThat(lastWinningBoard.getFinalScore()).isEqualTo(31755);
    }

    public List<List<String>> getBingoBoards(String fileName) throws IOException {
        var allLinesRead = Files.readAllLines(Paths.get(fileName));
        var nonBlankLines = allLinesRead.subList(1, allLinesRead.size()).stream().filter(s -> !s.isBlank()).collect(Collectors.toList());

        return IntStream.range(0, (nonBlankLines.size() / 5))
                .mapToObj(i -> nonBlankLines.subList(5 * i, (5 * i) + 5)).collect(Collectors.toList());
    }

    private String getBingoInput(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).get(0);
    }

    private BingoBoard createBingoBoardsAndFindFirstWinningBoard(String fileName) throws IOException {
        var allBingoBoardLines = getBingoBoards(fileName);

        ArrayList<BingoBoard> bingoBoards = getBingoBoardsFromAllLines(allBingoBoardLines);

        for (String input : getBingoInput(fileName).trim().split(",")) {
            bingoBoards.forEach(b -> b.drawNumber(Integer.parseInt(input)));
            var wonBoard = bingoBoards.stream().filter(BingoBoard::isWon).findFirst();
            if (wonBoard.isPresent()) {
                return wonBoard.get();
            }
        }
        return null;
    }

    private BingoBoard createBingoBoardsAndFindLastWinningBoard(String fileName) throws IOException {
        var bingoBoardLines = getBingoBoards(fileName);
        ArrayList<BingoBoard> bingoBoards = getBingoBoardsFromAllLines(bingoBoardLines);

        BingoBoard lastWinningBoard = null;

        for (String input : getBingoInput(fileName).trim().split(",")) {
            bingoBoards.forEach(b -> b.drawNumber(Integer.parseInt(input)));
            if (bingoBoards.stream().anyMatch(b -> !b.isWon())) {
                lastWinningBoard = bingoBoards.stream().filter(b -> !b.isWon()).findFirst().get();
            }
            if (bingoBoards.stream().allMatch(BingoBoard::isWon)) {
                return lastWinningBoard;
            }
        }
        return null;
    }

    private ArrayList<BingoBoard> getBingoBoardsFromAllLines(List<List<String>> allBingoBoardLines) {
        var bingoBoards = new ArrayList<BingoBoard>();
        for (List<String> bingoBoardInputLines : allBingoBoardLines) {
            bingoBoards.add(new BingoBoard(bingoBoardInputLines));
        }
        return bingoBoards;
    }

}
