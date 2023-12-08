import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day07;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day07Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/PuzzleInput.txt"))
                        .toURI()));

        final Day07 part1Test = new Day07(testInput);
        final Day07 part1Puzzle = new Day07(puzzleInput);

        Assertions.assertThat(part1Test.totalWinnings()).isEqualTo(6440);
        Assertions.assertThat(part1Puzzle.totalWinnings()).isEqualTo(250957639);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/PuzzleInput.txt"))
                        .toURI()));

        final Day07 part2Test = new Day07(testInput);
        final Day07 part2Puzzle = new Day07(puzzleInput);

        Assertions.assertThat(part2Test.totalWinningsJoker()).isEqualTo(5905);
        Assertions.assertThat(part2Puzzle.totalWinningsJoker()).isEqualTo(251515496);
    }
}
