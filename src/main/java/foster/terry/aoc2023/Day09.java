package foster.terry.aoc2023;

import java.util.*;

public class Day09 {
    final private List<List<Long>> allHistory = new ArrayList<>();
    public Day09(final List<String> input) {
        for (final String line : input) {
            allHistory.add(Arrays.stream(line.split(" +")).mapToLong(Long::parseLong).boxed().toList());
        }
    }

    public long getNextValTotal() {
        long retVal = 0;
        for (final List<Long> history : allHistory) {
            retVal += findNextValue(history);
        }
        return retVal;
    }
    public long getPrevValTotal() {
        long retVal = 0;
        for (final List<Long> history : allHistory) {
            retVal += findPrevValue(history);
        }
        return retVal;

    }

    private long findNextValue(final List<Long> history) {
        List<List<Long>> dt = getDTSet(history);
        long toAdd = 0;
        for (int x = dt.size()-1; x >= 0; x--) {
            toAdd = dt.get(x).get(dt.get(x).size()-1) + toAdd;
            dt.get(x).add(toAdd);
        }
        return history.get(history.size()-1) + toAdd;
    }
    private long findPrevValue(final List<Long> history) {
        List<List<Long>> dt = getDTSet(history);
        long toAdd = 0;
        for (int x = dt.size()-1; x >= 0; x--) {
            toAdd = dt.get(x).get(0) - toAdd;
            dt.get(x).add(0, toAdd);
        }

        return history.get(0) - toAdd;
    }

    private List<List<Long>> getDTSet(List<Long> itemDifferences) {
        List<List<Long>> dt = new ArrayList<>();
        while (itemDifferences.stream().distinct().count() > 1) {
            List<Long> tempDiffs = new ArrayList<>();
            for (int x = 0; x < itemDifferences.size()-1; x++) {
                tempDiffs.add(itemDifferences.get(x+1) - itemDifferences.get(x));
            }
            dt.add(tempDiffs);
            itemDifferences = new ArrayList<>(tempDiffs);
        }
        return dt;
    }
}
