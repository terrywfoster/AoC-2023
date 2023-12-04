package foster.terry.aoc2023;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {

    private final List<Set<Integer>> winningNumberSet = new ArrayList<>();
    private final List<Set<Integer>> myNumberSet = new ArrayList<>();

    public Day04(final List<String> input) {
        for(String card : input) {
            Set<Integer> winningNumbers = Arrays.stream(card.substring(card.indexOf(":")+2, card.indexOf("|")-1).trim().replace("  "," ").split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
            Set<Integer> myNumbers = Arrays.stream(card.substring(card.indexOf("|")+2).trim().replace("  "," ").split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
            winningNumberSet.add(winningNumbers);
            myNumberSet.add(myNumbers);
        }
    }

    public int scratchCardTotalPoints() {
        int totalPoints = 0;
        for (int z = 0; z < winningNumberSet.size(); z++) {
            int cardPoints = 0;
            Set<Integer> winningNumbers = winningNumberSet.get(z);
            Set<Integer> myNumbers = myNumberSet.get(z);

            winningNumbers.retainAll(myNumbers);
            if (!winningNumbers.isEmpty()) {
                cardPoints = 1;
                for (int x = 1; x < winningNumbers.size(); x++) {
                    cardPoints *= 2;
                }
            }

            totalPoints += cardPoints;
        }


        return totalPoints;
    }

    public int totalScratchCards() {
        final int[] cardCounts = new int[winningNumberSet.size()];

        for(int x = 0; x < winningNumberSet.size(); x++) {
            Set<Integer> winningNumbers = winningNumberSet.get(x);
            Set<Integer> myNumbers = myNumberSet.get(x);
            winningNumbers.retainAll(myNumbers);

            int wonCards = winningNumbers.size();
            int currentCardCount = cardCounts[x] + 1;
            for (int c = 0; c < currentCardCount; c++) {
                for (int w = 0; w < wonCards; w++) {
                    cardCounts[x+1+w]++;
                }
            }
        }

        return IntStream.of(cardCounts).sum() + winningNumberSet.size();
    }
}
