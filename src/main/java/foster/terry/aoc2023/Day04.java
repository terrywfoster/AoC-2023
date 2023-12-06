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
            int cardPoints = 0;
            if (!game.isEmpty()) {
                cardPoints = 1;
                for (int x = 1; x < game.size(); x++) {
                    cardPoints *= 2;
                }
            }

            totalPoints += cardPoints;
        }


        return totalPoints;
    }

    public int totalScratchCards() {
        final int[] cardCounts = new int[gameMatches.size()];
        for(int x = 0; x < gameMatches.size(); x++) {
            int wonCards = gameMatches.get(x).size();
            int currentCardCount = cardCounts[x] + 1;
            for (int c = 0; c < currentCardCount; c++) {
                for (int w = 0; w < wonCards; w++) {
                    cardCounts[x+1+w]++;
                }
            }

        }

        return IntStream.of(cardCounts).sum() + gameMatches.size();
    }
}
