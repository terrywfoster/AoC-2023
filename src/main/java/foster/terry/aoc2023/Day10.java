package foster.terry.aoc2023;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Day10 {
     final private char[][] grid;
     private Point start;

     final private char[] possMoves = "UDLR".toCharArray();
     final private HashMap<Character, String> shapeMoves = new HashMap<>();
    public Day10 (List<String> input) {
        grid = new char[input.size()][input.get(0).length()];
        for(int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
            if (input.get(y).contains("S")) {
                start = new Point(input.get(y).indexOf('S'), y);
            }
        }

        shapeMoves.put('|',"UD");
        shapeMoves.put('-',"LR");
        shapeMoves.put('L',"UR");
        shapeMoves.put('J',"LU");
        shapeMoves.put('7',"LD");
        shapeMoves.put('F',"DR");
    }

    public int farthestSteps() {
        String firstDirections = findFirstMoves();
        Point left = move(start, firstDirections.charAt(0)+"");
        Point right = move(start, firstDirections.charAt(1)+"");
        Point lastLeft = new Point(start);
        Point lastRight = new Point(start);



        int steps = 1;
        //System.out.println(steps + ": " + left + "=" + grid[left.y][left.x] + " & " + right + "=" + grid[right.y][right.x]);
        while (!left.equals(right)) {
            steps++;

            //System.out.println("L MOVES " + findNextMove(left, lastLeft));
            Point nextLeft = move(left, findNextMove(left, lastLeft));
            //System.out.println("R MOVES " + findNextMove(right, lastRight));
            Point nextRight = move(right, findNextMove(right, lastRight));

            grid[left.y][left.x] = '0';
            grid[right.y][right.x] = '0';

            lastLeft = new Point(left);
            lastRight = new Point(right);

            left = new Point(nextLeft);
            right = new Point(nextRight);
            //System.out.println(steps + ": " + left + "=" + grid[left.y][left.x] + " & " + right + "=" + grid[right.y][right.x]);

        }




        return steps;
    }

    public long enclosedTiles() {
        farthestSteps();

        //Testing Part2
        long surrCount = 0;
        for(int y=0; y<grid.length; y++) {
            for(int x=0; x<grid[y].length; x++) {
                //System.out.print(grid[y][x] == '.' ? "#" : grid[y][x]);
                Point p = new Point(x,y);
                if (grid[y][x] != '0' && checkDir("U", p) && checkDir("D", p) && checkDir("L", p) && checkDir("R", p)) {
                    //System.out.println(p);
                    surrCount++;
                    System.out.print('#');
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.print("\n");
        }
        System.out.println(surrCount);

        return surrCount;
    }
    private String findNextMove(Point p, Point lastP) {
        char[] possShapeMoves = shapeMoves.get(grid[p.y][p.x]).toCharArray();
        int dirIndex = 0;
        while (true) {
            String dir = possShapeMoves[dirIndex] + "";
            //System.out.println("Checking " + dir);
            if (canMove(p, dir, lastP)) {
                //System.out.println(dir + " works!");
                return dir;
            }
            dirIndex++;
        }
    }
    private boolean canMove(Point p, String dir, Point lastP) {
        try {
            switch (dir) {
                case "U" -> {
                    return "|7F".indexOf((grid[p.y-1][p.x])) >= 0 && !new Point(p.x, p.y - 1).equals(lastP);
                }
                case "D" -> {
                    return "|LJ".indexOf((grid[p.y+1][p.x])) >= 0 && !new Point(p.x, p.y+1).equals(lastP);
                }
                case "L" -> {
                    return "-LF".indexOf((grid[p.y][p.x-1])) >= 0 && !new Point(p.x-1, p.y).equals(lastP);
                }
                case "R" -> {
                    return "-J7".indexOf((grid[p.y][p.x+1])) >= 0 && !new Point(p.x+1, p.y).equals(lastP);
                }
                default -> {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    private boolean checkAdjacents(Point p) {
        try {
            if (grid[p.y-1][p.x] == '0'
                && grid[p.y+1][p.x] == '0'
                && grid[p.y][p.x-1] == '0'
                && grid[p.y][p.x+1] == '0'
            ) {
                return true;
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
        return false;
    }
    private boolean checkDir(String dir, Point p) {
        try {
            switch (dir) {
                case "U" -> {
                    for (int z = p.y-1; z >= 0; z--) {
                        if (grid[z][p.x] == '0') { return true; }
                    }
                    return false;
                    //return grid[p.y-1][p.x] == '0';
                }
                case "D" -> {
                    //return grid[p.y+1][p.x] == '0';
                    for (int z = p.y+1; z < grid.length; z++) {
                        if (grid[z][p.x] == '0') { return true; }
                    }
                    return false;
                }
                case "L" -> {
                    //return grid[p.y][p.x-1] == '0';
                    for (int z = p.x-1; z >= 0; z--) {
                        if (grid[p.y][z] == '0') { return true; }
                        /*
                        else {
                            Point np = new Point(z, p.y);
                            return isPath(np, "U") && isPath(np, "D") && isPath(np, "L") && isPath(np, "R");
                        }
                         */
                    }
                    return false;
                }
                case "R" -> {
                    //return grid[p.y][p.x+1] == '0';
                    for (int z = p.x+1; z < grid[p.y].length; z++) {
                        if (grid[p.y][z] == '0') { return true; }
                    }
                    return false;
                }
                default -> {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    private Point move(Point p, String dir) {
        switch (dir) {
            case "U" -> {
                return new Point(p.x, p.y-1);
            }
            case "D" -> {
                return new Point(p.x, p.y+1);
            }
            case "L" -> {
                return new Point(p.x-1, p.y);
            }
            case "R" -> {
                return new Point(p.x+1, p.y);
            }
            default -> {
                return p;
            }
        }

    }

    private String findFirstMoves() {
        StringBuilder firstDirections = new StringBuilder();
        int dirIndex = 0;
        while (firstDirections.length() < 2) {
            String dir = possMoves[dirIndex] + "";
            if (canMove(start, dir, start)) {
                firstDirections.append(dir);
            }
            dirIndex++;
        }
        return firstDirections.toString();
    }
}
