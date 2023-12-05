package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.*;

public class Day05 {
    final private List<String> input;
    final private List<Seed> seeds = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> seed2SoilMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> soil2FertMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> fert2WaterMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> water2LightMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> light2TempMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> temp2HumMap = new ArrayList<>();
    final private List<ImmutableTriple<Long, Long, Long>> hum2LocMap = new ArrayList<>();

    public Day05(List<String> puzzleInput) {
        input = puzzleInput;
        populateMaps();
    }

    public long lowestLocationRange() {
        long lowest = Long.MAX_VALUE;
        long[] seedRangeArray = Arrays.stream(input.get(0).substring(input.get(0).indexOf(":") + 2).trim().split(" +")).mapToLong(Long::parseLong).toArray();
        for (int x = 0; x < seedRangeArray.length; x = x+2) {
            for(long z = seedRangeArray[x]; z < seedRangeArray[x] + seedRangeArray[x+1]; z++) {
                lowest = Math.min(lowest, (new Seed(z)).location);
            }
        }
        return lowest;
    }

    public long lowestLocationDiscrete() {
        long[] seedArray = Arrays.stream(input.get(0).substring(input.get(0).indexOf(":") + 2).trim().split(" +")).mapToLong(Long::parseLong).toArray();
        for (long id : seedArray) {
            seeds.add(new Seed(id));
        }
        Seed lowestLocation = seeds.stream()
                .min(Comparator.comparing(Seed::getLocation))
                .orElseThrow(NoSuchElementException::new);
        return lowestLocation.location;
    }


    private class Seed {
        public Seed(long id) {
            this.id = id;
            this.setLocation();
        }
        public long id;
        public long location;

        public void setLocation() {
            this.location = calcValFromMap(this.getHumidity(), hum2LocMap);
        }
        public long getLocation() {
            return calcValFromMap(this.getHumidity(), hum2LocMap);
        }
        public long getHumidity() {
            return calcValFromMap(this.getTemperature(), temp2HumMap);
        }
        public long getTemperature() {
            return calcValFromMap(this.getLight(), light2TempMap);
        }
        public long getLight() {
            return calcValFromMap(this.getWater(), water2LightMap);
        }
        public long getWater() {
            return calcValFromMap(this.getFertilizer(), fert2WaterMap);
        }
        public long getFertilizer() {
            return calcValFromMap(this.getSoil(), soil2FertMap);
        }
        public long getSoil() {
            return calcValFromMap(this.getSeed(), seed2SoilMap);
        }
        public long getSeed() {
            return this.id;
        }
    }

    private void addToMap(String line, List<ImmutableTriple<Long, Long, Long>> map) {
        long[] mapArray = Arrays.stream(line.substring(line.indexOf(":") + 1).trim().split(" +")).mapToLong(Long::parseLong).toArray();
        map.add(new ImmutableTriple<>(mapArray[1], mapArray[1] + mapArray[2], mapArray[0] - mapArray[1]));

    }
    private long calcValFromMap(long val, List<ImmutableTriple<Long, Long, Long>> map) {
        long retVal = val;
        for (ImmutableTriple<Long, Long, Long> range : map) {
            if (range.left <= val && val < range.middle) {
                retVal += range.right;
            }
        }
        return retVal;
    }
    private void populateMaps() {
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains("seed-to-soil")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, seed2SoilMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("soil-to-fertilizer")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, soil2FertMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("fertilizer-to-water")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, fert2WaterMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("water-to-light")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, water2LightMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("light-to-temperature")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, light2TempMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("temperature-to-humidity")) {
                line = iterator.next();
                while (!line.isBlank()) {
                    addToMap(line, temp2HumMap);
                    line = iterator.next();
                }
            }
            else if (line.contains("humidity-to-location")) {
                line = iterator.next();
                while (iterator.hasNext() && !line.isBlank()) {
                    addToMap(line, hum2LocMap);
                    line = iterator.next();
                }
            }
        }

    }
}
