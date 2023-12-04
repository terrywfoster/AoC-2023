package foster.terry.aoc2023;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class Day03 {
    final String[][] grid = new String[140][141];
    final List<ImmutablePair<Integer, Integer>> gearsNearStars = new ArrayList<>();

    public Day03(List<String> input) {
        int z = 1000;
        for (int y = 0; y < input.size(); y++) {
            char[] lineChars = input.get(y).toCharArray();
            for(int x = 0; x < input.get(y).length(); x++) {
                char thisChar = lineChars[x];
                if (thisChar == '*') {
                    grid[y][x] = ((char)z) + "";
                    z++;
                }
                else if (thisChar != '.') {
                    grid[y][x] = thisChar + "";
                }
                else {
                    grid[y][x] = "";
                }
            }
        }
    }

    public int partNumberTotal() {
        int total = 0;
        for (int y = 0; y < grid.length; y++) {
            StringBuilder foundNum = new StringBuilder();
            boolean symbolNear = false;
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != null && grid[y][x].matches("-?\\d+(\\.\\d+)?")) {
                        foundNum.append(grid[y][x]);

                        //Determine if symbol is near
                        if (!symbolNear) { symbolNear = isSymbolNear(y, x); }
                }
                else if (!foundNum.isEmpty()) {
                    if (symbolNear) { total += Integer.parseInt(foundNum.toString()); }

                    foundNum = new StringBuilder();
                    symbolNear = false;
                }
            }
        }
        return total;
    }

    public int gearRatios() {
        int totalRatios = 0;
        for (int y = 0; y < grid.length; y++) {
            StringBuilder foundNum = new StringBuilder();
            LinkedHashSet<Integer> starsNear = new LinkedHashSet<>();

            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != null && grid[y][x].matches("-?\\d+(\\.\\d+)?")) {
                        foundNum.append(grid[y][x]);

                        //Determine What Stars are Near
                        starsNear.addAll(findStarsNear(y,x));
                }
                else if (!foundNum.isEmpty()) {
                    for(int star : starsNear) {
                        gearsNearStars.add(new ImmutablePair<>(star, Integer.parseInt(foundNum.toString())));
                    }

                    foundNum = new StringBuilder();
                    starsNear = new LinkedHashSet<>();
                }
            }
        }

        for(int z = 1000; z < 1365; z++) {
            int ratio = 1;
            int numFound = 0;
            for(ImmutablePair<Integer, Integer> star : gearsNearStars) {
                if (star.left == z) {
                    numFound++;
                    ratio *= star.right;
                }
            }
            if (numFound == 2) {
                totalRatios += ratio;
            }
        }

        return totalRatios;
    }

    private boolean isSymbolNear(int yGrid, int xGrid) {
        for (int z = 0; z < 3; z++) {
            for(int y = yGrid - 1; y < yGrid + 2; y++) {
                for(int x = xGrid - 1; x < xGrid + 2; x++) {
                    if (0 <= y && y < grid.length && 0 <= x && x < grid[y].length) {
                        if (grid[y][x] != null) {
                            if (!grid[y][x].matches("-?\\d+(\\.\\d+)?") && !grid[y][x].isEmpty()) {
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }
    private Set<Integer> findStarsNear(int yGrid, int xGrid) {
        LinkedHashSet<Integer> retVal = new LinkedHashSet<>();
        for (int z = 0; z < 3; z++) {
            for(int y = yGrid - 1; y < yGrid + 2; y++) {
                for(int x = xGrid - 1; x < xGrid + 2; x++) {
                    if (0 <= y && y < grid.length && 0 <= x && x < grid[y].length) {
                        if (grid[y][x] != null && !grid[y][x].isEmpty() && (int)(grid[y][x].toCharArray()[0]) >= 1000) {
                            retVal.add((int)(grid[y][x].toCharArray()[0]));
                        }
                    }
                }
            }
        }
        return retVal;
    }


}
