package foster.terry.aoc2023;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {


    private final List<Set<Integer>> gameMatches = new ArrayList<>();

    public Day04(final List<String> input) {
        for(String card : input) {
            Set<Integer> winningNumbers = Arrays.stream(card.substring(card.indexOf(":")+1, card.indexOf("|")-1).trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toSet());
            Set<Integer> myNumbers = Arrays.stream(card.substring(card.indexOf("|")+1).trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toSet());

            winningNumbers.retainAll(myNumbers);
            gameMatches.add(winningNumbers);
        }
    }

    public int scratchCardTotalPoints() {
        int totalPoints = 0;
        for (Set<Integer> game : gameMatches) {
            if (!game.isEmpty()) {
                totalPoints += (int) Math.pow(2, game.size()-1);
            }
        }
        return totalPoints;
    }

    public int totalScratchCards() {
        final int[] cardCounts = new int[gameMatches.size()];
        for(int x = 0; x < gameMatches.size(); x++) {
            cardCounts[x]++;
            int currentCardCount = cardCounts[x];
            int wonCards = gameMatches.get(x).size();
            for (int c = 1; c <= wonCards; c++) {
                cardCounts[x+c] += currentCardCount;
            }

        }
        return IntStream.of(cardCounts).sum();
    }
}
