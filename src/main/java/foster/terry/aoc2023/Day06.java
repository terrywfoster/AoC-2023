package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 {
    private final List<Pair<Long, Long>> races = new ArrayList<>();

    public Day06(List<String> input) {
        final String [] times = input.get(0).split(" +");
        final String [] distances = input.get(1).split(" +");
        for(int x = 1; x < times.length; x++) {
            races.add(Pair.of(Long.parseLong(times[x]), Long.parseLong(distances[x])));
        }
    }

    public long productOfWaysToWin() {
        long waysToWinProduct = 1;
        for(final Pair<Long, Long> race : races) {
            waysToWinProduct *= waysToWin(race.getLeft(), race.getRight());
        }
        return waysToWinProduct;
    }

    public long oneRaceWaysToWin() {
        final long time =  Long.parseLong(races.stream()
                .mapToLong(Pair::getLeft)
                .mapToObj(l -> ((Long) l).toString())
                .collect(Collectors.joining()));
        final long distance =  Long.parseLong(races.stream()
                .mapToLong(Pair::getRight)
                .mapToObj(l -> ((Long) l).toString())
                .collect(Collectors.joining()));

        return waysToWin(time, distance);
    }

    public long waysToWin(final long time, final long distance) {
        long maxWayToWin = 0;
        for(long x = time-1; x > 0; x--) {
            if (distance < x * (time-x)) {
                maxWayToWin = x;
                break;
            }
        }
        return maxWayToWin - (time - maxWayToWin) + 1;
    }


}
