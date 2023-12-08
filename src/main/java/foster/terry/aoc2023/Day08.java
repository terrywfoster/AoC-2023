package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 {
    private final char[] moves;
    private final HashMap<String, Pair<String, String>> maps = new HashMap<>();

    public Day08(List<String> input) {
        moves = input.get(0).toCharArray();
        for (final String line : input) {
            if (line.contains("=")) {
                String[] keys = line.split(" = \\(|, |\\)");
                maps.put(keys[0], Pair.of(keys[1], keys[2]));
            }
        }
    }

    public long ghostMoves() {
        HashMap<String, Pair<String, String>> startMaps = maps.entrySet().stream().filter(x -> x.getKey().charAt(2) == 'A').collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        long lcmMoves = 0;
        for(final Map.Entry<String, Pair<String, String>> map : startMaps.entrySet()) {
            if (lcmMoves == 0) { lcmMoves = getMovesToEnd(map.getKey(), ".+Z$"); }
            lcmMoves = lcm(lcmMoves, getMovesToEnd(map.getKey(), ".+Z$"));
        }
        return lcmMoves;
    }

    public long getMovesToEnd(String startKey, String endKey) {
        long moveCount = 0;
        String key = startKey;
        int moveIndex = 0;
        while (true) {
            moveCount++;

            switch (moves[moveIndex]) {
                case 'L' -> key = maps.get(key).getLeft();
                case 'R' -> key = maps.get(key).getRight();
            }
            if (key.matches(endKey)) { return moveCount; } //FOUND END
            moveIndex++;
            if (moveIndex == moves.length) { moveIndex = 0; } //RESET
        }
    }

    private static long gcd(long x, long y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    public static long lcm(long steps1, long steps2) {
        if (steps1 == 0 || steps2 == 0) {
            return 0;
        } else {
            return Math.abs(steps1 * steps2) / gcd(steps1, steps2);
        }
    }
}
