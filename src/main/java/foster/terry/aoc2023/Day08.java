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
                maps.put(line.substring(0,3), Pair.of(line.substring(7, 10), line.substring(12, 15)));
            }
        }
    }

    public long howManyMoves() {
        return getMovesToEnd("AAA", 1);
    }

    public long ghostMoves() {
        HashMap<String, Pair<String, String>> startMaps = maps.entrySet().stream().filter(x -> x.getKey().charAt(2) == 'A').collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        long lcmMoves = 0;
        for(final Map.Entry<String, Pair<String, String>> map : startMaps.entrySet()) {
            if (lcmMoves == 0) { lcmMoves = getMovesToEnd(map.getKey(), 2); }
            lcmMoves = lcm(lcmMoves, getMovesToEnd(map.getKey(), 2));
        }
        return lcmMoves;
    }

    private long getMovesToEnd(String startKey, int part) {
        long moveCount = 1L;
        String key = maps.get(startKey).getLeft();
        if (moves[0] == 'R') { key = maps.get(startKey).getRight(); }
        for (int x = 1; x < moves.length; x++) {
            moveCount++;

            switch (moves[x]) {
                case 'L' -> key = maps.get(key).getLeft();
                case 'R' -> key = maps.get(key).getRight();
            }
            if (key.equals("ZZZ") && part == 1) { break; } //FOUND ZZZ
            if (key.charAt(2) == 'Z' && part == 2) { break; } //FOUND Z
            if (x == moves.length-1) { x = -1; } //RESET
        }
        return moveCount;
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
