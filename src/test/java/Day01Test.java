import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day01;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day01Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/PuzzleInput.txt"))
                        .toURI()));

        final Day01 part1Test = new Day01(testInput);
        final Day01 part1Puzzle = new Day01(puzzleInput);

        //Assertions.assertThat(part1Test.totalTopCalories(1)).isEqualTo(24000);
        //Assertions.assertThat(part1Puzzle.totalTopCalories(1)).isEqualTo(70698);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/PuzzleInput.txt"))
                        .toURI()));

        final Day01 part2Test = new Day01(testInput);
        final Day01 part2Puzzle = new Day01(puzzleInput);

        //Assertions.assertThat(part2Test.totalTopCalories(3)).isEqualTo(45000);
        //Assertions.assertThat(part2Puzzle.totalTopCalories(3)).isEqualTo(206643);
    }
}
