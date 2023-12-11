import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day10;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day10Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/PuzzleInput.txt"))
                        .toURI()));

        final Day10 part1Test = new Day10(testInput);
        final Day10 part1Puzzle = new Day10(puzzleInput);

        Assertions.assertThat(part1Test.farthestSteps()).isEqualTo(8);
        Assertions.assertThat(part1Puzzle.farthestSteps()).isEqualTo(6733);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/Part2TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/PuzzleInput.txt"))
                        .toURI()));

        final Day10 part2Test = new Day10(testInput);
        final Day10 part2Puzzle = new Day10(puzzleInput);

        Assertions.assertThat(part2Test.enclosedTiles()).isEqualTo(8);
        Assertions.assertThat(part2Puzzle.enclosedTiles()).isEqualTo(0);
    }
}
