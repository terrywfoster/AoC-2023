package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

    private final List<List<ImmutableTriple<Integer, Integer, Integer>>> games = new ArrayList<>();


    public Day02 (final List<String> input) {
        for (String game : input) {
            game = game.substring(game.indexOf(":") + 2);

            List<ImmutableTriple<Integer, Integer, Integer>> gameSets = new ArrayList<>();

            List<String> sets = Arrays.stream(game.split("; ")).toList();
            for(final String set : sets) {
                List<String> colors = Arrays.stream(set.split(", ")).toList();

                MutableTriple<Integer, Integer, Integer> setValues = new MutableTriple<>(0,0,0);

                for(final String color : colors) {
                    List<String> pair = Arrays.stream(color.split(" ")).toList();

                    int count = Integer.parseInt(pair.get(0));
                    switch (pair.get(1)) {
                        case "red":
                            setValues.left = count;
                            break;
                        case "green":
                            setValues.middle = count;
                            break;
                        case "blue":
                            setValues.right = count;
                            break;
                    }
                }
                gameSets.add(new ImmutableTriple<>(setValues.left, setValues.middle, setValues.right));
            }
            games.add(gameSets);
        }
    }

    public int totalImpossibleGames(int maxRed, int maxGreen, int maxBlue) {
        int totalScore = 0;
        for (int x = 0; x < games.size(); x++) {
            boolean possible = true;
            List<ImmutableTriple<Integer, Integer, Integer>> game = games.get(x);
            for (ImmutableTriple<Integer, Integer, Integer> set : game) {
                if (set.left > maxRed || set.middle > maxGreen || set.right > maxBlue) {
                    possible = false;
                    break;
                }
            }
            if (possible) { totalScore += x + 1; }
        }
        return totalScore;
    }

    public int power() {
        int totalPower = 0;
        for (List<ImmutableTriple<Integer, Integer, Integer>> game : games) {
            int maxRed = 0, maxGreen = 0, maxBlue = 0;
            for (ImmutableTriple<Integer, Integer, Integer> set : game) {
                maxRed = Math.max(maxRed, set.left);
                maxGreen = Math.max(maxGreen, set.middle);
                maxBlue = Math.max(maxBlue, set.right);
            }
            totalPower += maxRed * maxGreen * maxBlue;
        }

        return totalPower;
    }
}
