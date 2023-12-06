package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    private final List<Pair<Long, Long>> races = new ArrayList<>();
    public Day06(List<String> input, boolean single) {
        String[] times = input.get(0).split(" +");
        String[] distances = input.get(1).split(" +");
        if (single) {
            long time = Long.parseLong(String.join("",Arrays.copyOfRange(times, 1, times.length)));
            long distance = Long.parseLong(String.join("",Arrays.copyOfRange(distances, 1, distances.length)));
            races.add(Pair.of(time, distance));
        }
        else {
            for(int x = 1; x < times.length; x++) {
                races.add(Pair.of(Long.parseLong(times[x]), Long.parseLong(distances[x])));
            }
        }
    }

    public long productOfWaysToWin() {
        long waysToWinProduct = 1;
        for(Pair<Long, Long> race : races) {
            waysToWinProduct *= waysToWin(race.getLeft(), race.getRight());
        }
        return waysToWinProduct;
    }

    public long waysToWin(long time, long distance) {
        long maxWayToWin = 0;
        for(long x = time-1; x > 0; x--) {
            if (distance < x * (time-x)) {
                maxWayToWin = x;
                break;
            }
        }
        long minWayToWin = 0;
        for(long x = 1; x < maxWayToWin; x++) {
            if (distance < x * (time-x)) {
                minWayToWin = x;
                break;
            }
        }
        return maxWayToWin - minWayToWin + 1;

    }


}
