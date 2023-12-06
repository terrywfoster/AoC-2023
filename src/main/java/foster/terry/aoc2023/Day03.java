package foster.terry.aoc2023;

import java.awt.*;
import java.util.*;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;


public class Day03 {
    final char[][] grid;
    final List<Point> gearsNearStars = new ArrayList<>();
    final List<Pair<Integer, Set<Integer>>> gears = new ArrayList<>();
    final List<Integer> stars = new ArrayList<>();

    public Day03(List<String> input) {
        grid = new char[input.size() + 1][input.get(0).length() + 1];

        int z = 1000;
        for (int y = 0; y < input.size(); y++) {
            char[] lineChars = input.get(y).toCharArray();
            for(int x = 0; x < input.get(y).length(); x++) {
                char thisChar = lineChars[x];
                if (thisChar == '*') {
                    grid[y][x] = (char)z;
                    stars.add(z);
                    z++;
                }
                else {
                    grid[y][x] = thisChar;
                }
            }
        }
    }

    public int partNumberTotal() {
        int total = 0;
        for (int y = 0; y < grid.length; y++) {
            StringBuilder foundDigits = new StringBuilder();
            boolean symbolNear = false;
            for (int x = 0; x < grid[y].length; x++) {
                char thisChar = grid[y][x];
                if ('0' <= thisChar && thisChar <= '9') {
                    foundDigits.append(thisChar);

                    //Determine if symbol is near
                    if (!symbolNear) { symbolNear = isSymbolNear(y, x); }
                }
                else if (!foundDigits.isEmpty()) {
                    if (symbolNear) {
                        //System.out.println(foundDigits.toString());
                        total += Integer.parseInt(foundDigits.toString());
                    }

                    foundDigits = new StringBuilder();
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
            Set<Integer> starsNear = new HashSet<>();

            for (int x = 0; x < grid[y].length; x++) {
                //if (grid[y][x] != null && grid[y][x].matches("-?\\d+(\\.\\d+)?")) {
                char thisChar = grid[y][x];
                //System.out.println("#" + thisChar + "#");
                if ('0' <= thisChar && thisChar <= '9') {
                    foundNum.append(thisChar);

                    //Determine What Stars are Near
                    starsNear.addAll(findStarsNear(y,x));
                }
                else if (!foundNum.isEmpty()) {
                    for(int star : starsNear) {
                        gearsNearStars.add(new Point(star, Integer.parseInt(foundNum.toString())));
                    }
                    gears.add(Pair.of(Integer.parseInt(foundNum.toString()), starsNear));

                    foundNum = new StringBuilder();
                    starsNear = new LinkedHashSet<>();
                }
            }
        }

        for (final Integer star : stars) {
            int ratio = 1;
            int numFound = 0;
            for (final Pair<Integer, Set<Integer>> gear : gears) {
                if (gear.getRight().contains(star)) {
                    numFound++;
                    if (numFound > 2) {
                        break;
                    }
                    ratio *= gear.getLeft();
                }
            }
            if (numFound == 2) {
                totalRatios += ratio;
            }
        }

        return totalRatios;
    }

    private boolean isSymbolNear(int yGrid, int xGrid) {
        for(int y = yGrid - 1; y < yGrid + 2; y++) {
            for(int x = xGrid - 1; x < xGrid + 2; x++) {
                if (0 <= y && y < grid.length && 0 <= x && x < grid[y].length) {
                    char thisChar = grid[y][x];
                    if ((thisChar < '0' || '9' < thisChar) && thisChar != '.' && thisChar != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private Set<Integer> findStarsNear(int yGrid, int xGrid) {
        LinkedHashSet<Integer> retVal = new LinkedHashSet<>();
        for(int y = yGrid - 1; y < yGrid + 2; y++) {
            for(int x = xGrid - 1; x < xGrid + 2; x++) {
                if (0 <= y && y < grid.length && 0 <= x && x < grid[y].length) {
                    char thisChar = grid[y][x];
                    if (thisChar >= 1000) {
                        retVal.add((int)thisChar);
                    }
                }
            }
        }
        return retVal;
    }


}
