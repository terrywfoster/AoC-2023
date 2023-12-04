import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day02;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day02Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/PuzzleInput.txt"))
                        .toURI()));

        final Day02 part1Test = new Day02(testInput);
        final Day02 part1Puzzle = new Day02(puzzleInput);

        Assertions.assertThat(part1Test.totalImpossibleGames(12, 13, 14)).isEqualTo(8);
        Assertions.assertThat(part1Puzzle.totalImpossibleGames(12, 13, 14)).isEqualTo(2285);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/PuzzleInput.txt"))
                        .toURI()));

        final Day02 part2Test = new Day02(testInput);
        final Day02 part2Puzzle = new Day02(puzzleInput);

        Assertions.assertThat(part2Test.power()).isEqualTo(2286);
        Assertions.assertThat(part2Puzzle.power()).isEqualTo(77021);
    }
}
