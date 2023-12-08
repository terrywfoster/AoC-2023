import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import foster.terry.aoc2023.Day08;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day08Test {
    @Test
    public void part1() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/TestInput.txt"))
                        .toURI()));
        final var testInput2 = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/Test2Input.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/PuzzleInput.txt"))
                        .toURI()));

        final Day08 part1Test = new Day08(testInput);
        final Day08 part1Test2 = new Day08(testInput2);
        final Day08 part1Puzzle = new Day08(puzzleInput);

        Assertions.assertThat(part1Test.getMovesToEnd("AAA","ZZZ")).isEqualTo(2);
        Assertions.assertThat(part1Test2.getMovesToEnd("AAA","ZZZ")).isEqualTo(6);
        Assertions.assertThat(part1Puzzle.getMovesToEnd("AAA","ZZZ")).isEqualTo(21389);
    }
    @Test
    public void part2() throws IOException, URISyntaxException {
        final var testInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/Part2TestInput.txt"))
                        .toURI()));
        final var puzzleInput = Files.readAllLines(Paths.get(
                Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/PuzzleInput.txt"))
                        .toURI()));

        final Day08 part2Test = new Day08(testInput);
        final Day08 part2Puzzle = new Day08(puzzleInput);

        Assertions.assertThat(part2Test.ghostMoves()).isEqualTo(6);
        Assertions.assertThat(part2Puzzle.ghostMoves()).isEqualTo(21083806112641L);
    }
}
