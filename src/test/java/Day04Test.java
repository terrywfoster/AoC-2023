import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day04;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day04Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/PuzzleInput.txt"))
                        .toURI()));

        final Day04 part1Test = new Day04(testInput);
        final Day04 part1Puzzle = new Day04(puzzleInput);

        Assertions.assertThat(part1Test.scratchCardTotalPoints()).isEqualTo(13);
        Assertions.assertThat(part1Puzzle.scratchCardTotalPoints()).isEqualTo(24160);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/PuzzleInput.txt"))
                        .toURI()));

        final Day04 part2Test = new Day04(testInput);
        final Day04 part2Puzzle = new Day04(puzzleInput);

        Assertions.assertThat(part2Test.totalScratchCards()).isEqualTo(30);
        Assertions.assertThat(part2Puzzle.totalScratchCards()).isEqualTo(5659035);
    }
}
