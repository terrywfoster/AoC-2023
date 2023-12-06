package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

public class Day05 {
    final private List<String> input;
    final private List<Seed> seeds = new ArrayList<>();

    final private List<List<Triple<Long, Long, Long>>> maps = new ArrayList<>();

    final long[] seedIdArray;

    public Day05(List<String> puzzleInput) {
        input = puzzleInput;
        populateMaps();
        seedIdArray = Arrays.stream(input.get(0).substring(input.get(0).indexOf(":") + 2).trim().split(" +")).mapToLong(Long::parseLong).toArray();
    }

    public long lowestLocationRange() {
        List<ImmutablePair<Long, Long>> ranges = new ArrayList<>();
        for (var x = 0; x < seedIdArray.length; x += 2) {
            ranges.add(new ImmutablePair<>(seedIdArray[x],seedIdArray[x+1]));
        }
        for (final List<Triple<Long, Long, Long>> map : maps) {
            ranges = nextRanges(map, ranges);

        }

        return ranges.stream().mapToLong(Pair::getLeft).min().orElse(Long.MAX_VALUE);

    }

    private List<ImmutablePair<Long, Long>> nextRanges(final List<Triple<Long, Long, Long>> map, final List<ImmutablePair<Long, Long>> ranges) {
        final List<ImmutablePair<Long, Long>> nextRanges = new ArrayList<>();

        for (final ImmutablePair<Long, Long> range : ranges) {
            long lower = range.getLeft();
            long count = range.getRight();
            for (final Triple<Long, Long, Long> section : map) {
                final long lowerBound = section.getMiddle();
                final long upperBound = section.getMiddle() + section.getRight() - 1;
                while (count > 0 && lower <= upperBound) {
                    if (lower < lowerBound) {
                        if ((lower + count - 1) < lowerBound) {
                            nextRanges.add(new ImmutablePair<>(lower, count));
                            count = 0;
                        } else {
                            long tempCount = lowerBound - lower;
                            nextRanges.add(new ImmutablePair<>(lower, tempCount));
                            lower = lowerBound;
                            count -= tempCount;
                        }
                    } else {
                        if ((lower + count -1) <= upperBound) {
                            nextRanges.add(new ImmutablePair<>((lower - section.getMiddle()) + section.getLeft(), count));
                            count = 0;
                        } else {
                            long tempCount = upperBound + 1 - lower;
                            nextRanges.add(new ImmutablePair<>((lower - section.getMiddle()) + section.getLeft(), tempCount));
                            lower = upperBound + 1;
                            count -= tempCount;
                        }
                    }
                }
            }
            if (count > 0) {
                nextRanges.add(new ImmutablePair<>(lower, count));
            }

        }
        return nextRanges;

    }

    public long lowestLocationDiscrete() {
        for (long id : seedIdArray) {
            seeds.add(new Seed(id));
        }
        seeds.sort((Comparator.comparingLong(Seed::getLocation)));
        return seeds.get(0).getLocation();
    }


    private class Seed {
        public Seed(long id) {
            this.id = id;
            this.setLocation();
        }
        public long id;
        public long location;

        public long getLocation() { return this.location; }
        public void setLocation() { this.location = calcValFromMap(this.getHumidity(), maps.get(6)); }
        public long getHumidity() { return calcValFromMap(this.getTemperature(), maps.get(5)); }
        public long getTemperature() {
            return calcValFromMap(this.getLight(), maps.get(4));
        }
        public long getLight() {
            return calcValFromMap(this.getWater(), maps.get(3));
        }
        public long getWater() {
            return calcValFromMap(this.getFertilizer(), maps.get(2));
        }
        public long getFertilizer() { return calcValFromMap(this.getSoil(), maps.get(1)); }
        public long getSoil() { return calcValFromMap(this.getSeed(), maps.get(0)); }
        public long getSeed() {
            return this.id;
        }
    }

    private Triple<Long, Long, Long> addToMap(final String line) {
        final long[] mapArray = Arrays.stream(line.trim().split(" +")).mapToLong(Long::parseLong).toArray();
        return new ImmutableTriple<>(mapArray[0], mapArray[1], mapArray[2]);

    }

    private long calcValFromMap(final long val, final List<Triple<Long, Long, Long>> map) {
        for (final Triple<Long, Long, Long> range : map) {
            if (range.getMiddle() <= val && val <= range.getMiddle() + range.getRight() - 1) {
                return val + (range.getLeft() - range.getMiddle());
            }
        }
        return val;
    }
    private void populateMaps() {
        int mapNum = -1;
        for (final String line : input) {
            if (!line.contains("seeds")) {
                if (line.contains("map")) {
                    mapNum++;
                    maps.add(new ArrayList<>());
                }
                else if (!line.isEmpty()) {
                    maps.get(mapNum).add(addToMap(line));

                }
            }
        }
        for (final var map : maps) {
            map.sort((Comparator.comparingLong(Triple::getMiddle)));
        }
    }
}
