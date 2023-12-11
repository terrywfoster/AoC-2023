import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day09;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day09Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/PuzzleInput.txt"))
                        .toURI()));

        final Day09 part1Test = new Day09(testInput);
        final Day09 part1Puzzle = new Day09(puzzleInput);

        Assertions.assertThat(part1Test.getNextValTotal()).isEqualTo(114);
        Assertions.assertThat(part1Puzzle.getNextValTotal()).isEqualTo(1955513104);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/PuzzleInput.txt"))
                        .toURI()));

        final Day09 part2Test = new Day09(testInput);
        final Day09 part2Puzzle = new Day09(puzzleInput);

        Assertions.assertThat(part2Test.getPrevValTotal()).isEqualTo(2);
        Assertions.assertThat(part2Puzzle.getPrevValTotal()).isEqualTo(1131);
    }
}
