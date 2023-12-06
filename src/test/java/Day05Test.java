import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day05;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day05Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/PuzzleInput.txt"))
                        .toURI()));

        final Day05 part1Test = new Day05(testInput);
        final Day05 part1Puzzle = new Day05(puzzleInput);

        Assertions.assertThat(part1Test.lowestLocationDiscrete()).isEqualTo(35);
        Assertions.assertThat(part1Puzzle.lowestLocationDiscrete()).isEqualTo(993500720);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/PuzzleInput.txt"))
                        .toURI()));

        final Day05 part2Test = new Day05(testInput);
        final Day05 part2Puzzle = new Day05(puzzleInput);

        Assertions.assertThat(part2Test.lowestLocationRangeNew()).isEqualTo(46);
        Assertions.assertThat(part2Puzzle.lowestLocationRangeNew()).isEqualTo(4917124);
    }
}
