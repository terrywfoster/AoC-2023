import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day06;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day06Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/PuzzleInput.txt"))
                        .toURI()));

        final Day06 part1Test = new Day06(testInput);
        final Day06 part1Puzzle = new Day06(puzzleInput);

        Assertions.assertThat(part1Test.productOfWaysToWin()).isEqualTo(288);
        Assertions.assertThat(part1Puzzle.productOfWaysToWin()).isEqualTo(32076);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/PuzzleInput.txt"))
                        .toURI()));

        final Day06 part2Test = new Day06(testInput);
        final Day06 part2Puzzle = new Day06(puzzleInput);

        Assertions.assertThat(part2Test.oneRaceWaysToWin()).isEqualTo(71503);
        Assertions.assertThat(part2Puzzle.oneRaceWaysToWin()).isEqualTo(34278221);
    }
}
