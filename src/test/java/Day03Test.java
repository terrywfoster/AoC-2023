import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day03;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day03Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/PuzzleInput.txt"))
                        .toURI()));

        final Day03 part1Test = new Day03(testInput);
        final Day03 part1Puzzle = new Day03(puzzleInput);

        Assertions.assertThat(part1Test.partNumberTotal()).isEqualTo(4361);
        Assertions.assertThat(part1Puzzle.partNumberTotal()).isEqualTo(536202);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/PuzzleInput.txt"))
                        .toURI()));

        final Day03 part2Test = new Day03(testInput);
        final Day03 part2Puzzle = new Day03(puzzleInput);

        Assertions.assertThat(part2Test.gearRatios()).isEqualTo(467835);
        Assertions.assertThat(part2Puzzle.gearRatios()).isEqualTo(78272573);
    }
}
