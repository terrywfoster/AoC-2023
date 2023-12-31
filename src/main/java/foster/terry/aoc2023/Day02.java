package foster.terry.aoc2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Day02 {

    private final List<Game> games = new ArrayList<>();


    public Day02 (final List<String> input) {
        for (String game : input) {
            game = game.substring(game.indexOf(":") + 2);

            Game thisGame = new Game();

            String[] sets = game.split("; ");
            for(final String set : sets) {
                String[] colors = set.split("[, ]+");

                int red = 0, green = 0, blue = 0;
                for(int x = 0; x < colors.length; x = x + 2) {
                    int count = Integer.parseInt(colors[x]);
                    switch (colors[x+1]) {
                        case "red" -> red = count;
                        case "green" -> green = count;
                        case "blue" -> blue = count;
                    }
                    thisGame.turns.add(new Turn(red, green, blue));
                }
            }
            games.add(thisGame);
        }
    }

    public int totalPossibleScore(int maxRed, int maxGreen, int maxBlue) {
        int totalScore = 0;
        for (int x = 0; x < games.size(); x++) {
            Turn maxColors = findMaxColors(games.get(x));
            if (maxColors.red <= maxRed && maxColors.green <= maxGreen && maxColors.blue <= maxBlue) {
                totalScore += x + 1;
            }
        }
        return totalScore;
    }

    public int power() {
        int totalPower = 0;
        for (Game game : games) {
            Turn maxColors = findMaxColors(game);
            totalPower += maxColors.red * maxColors.green * maxColors.blue;
        }

        return totalPower;
    }

    private Turn findMaxColors(Game game) {
        var setMaxRed = game.turns.stream().max(Comparator.comparing(Turn::getRed)).orElseThrow(NoSuchElementException::new);
        var setMaxGreen = game.turns.stream().max(Comparator.comparing(Turn::getGreen)).orElseThrow(NoSuchElementException::new);
        var setMaxBlue = game.turns.stream().max(Comparator.comparing(Turn::getBlue)).orElseThrow(NoSuchElementException::new);
        return new Turn(setMaxRed.red, setMaxGreen.green, setMaxBlue.blue);
    }

    private static class Turn {
        public int red;
        public int green;
        public int blue;
        public Turn(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
        public int getRed() {
            return this.red;
        }
        public int getGreen() {
            return this.green;
        }
        public int getBlue() {
            return this.blue;
        }
    }

    private static class Game {
        public List<Turn> turns;

        public Game() {
            this.turns = new ArrayList<>();
        }
    }
}
