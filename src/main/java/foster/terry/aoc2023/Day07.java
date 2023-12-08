package foster.terry.aoc2023;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day07 {
    private List<Hand> hands = new ArrayList<>();
    final public static Map<Character, Integer> cardScores = new HashMap<>();

    public Day07 (List<String> input) {
        cardScores.put('2',2);
        cardScores.put('3',3);
        cardScores.put('4',4);
        cardScores.put('5',5);
        cardScores.put('6',6);
        cardScores.put('7',7);
        cardScores.put('8',8);
        cardScores.put('9',9);
        cardScores.put('T',10);
        cardScores.put('J',11);
        cardScores.put('Q',12);
        cardScores.put('K',13);
        cardScores.put('A',14);

        for (String line : input) {
            String[] play = line.split(" ");
            Hand hand = new Hand(play[0], Integer.parseInt(play[1]));
            hands.add(hand);
        }
    }

    public long totalWinnings() {
        for (Hand hand : hands) {
            hand.handType = getHandType((int)hand.cardFrequency.values().toArray()[0], hand.cardFrequency.values().size() == 1 ? 0 : (int)hand.cardFrequency.values().toArray()[1]);
        }
        //Sort hands
        sortHands();

        return getTotalWinnings();
    }


    public long totalWinningsJoker() {
        cardScores.replace('J',1);

        for (Hand hand : hands) {
            hand.setHandScore(); //Update the Hand Score now that Jokers are worth less
            long jokerCount = hand.cardFrequency.getOrDefault(11, 0);
            hand.cardFrequency.remove(11);
            hand.handType = getHandType((hand.cardFrequency.values().isEmpty() ? 0 : (int)hand.cardFrequency.values().toArray()[0]) + (int)jokerCount, hand.cardFrequency.values().size() <= 1 ? 0 : (int)hand.cardFrequency.values().toArray()[1]);
        }
        sortHands();

        return getTotalWinnings();
    }

    private long getTotalWinnings() {
        long totalWinnings = 0;
        for(int x = 0; x < hands.size(); x++) {
            totalWinnings += (long) (x + 1) * hands.get(x).bid;
        }

        return totalWinnings;
    }
    private int getHandType(int highestSet, int nextHighestSet) {
        switch (highestSet) {
            case 5: return 1;
            case 4: return 2;
            case 3:
                if(nextHighestSet == 2) {
                    return 3;
                }
                else {
                    return 4;
                }
            case 2:
                if(nextHighestSet == 2) {
                    return 5;
                }
                else {
                    return 6;
                }
            default: return 7;
        }

    }

    private void sortHands() {
        hands = hands.stream().sorted(Comparator.comparing(Hand::getHandType).reversed()
                .thenComparing(Hand::getHandScore)).toList();
    }

    private static class Hand {
        public String cards;
        public int bid;
        public int handType;
        public String handScore;

        public Map<Integer, Integer> cardFrequency;

        public Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
            this.cardFrequency = cards.chars().mapToObj(c -> cardScores.get((char) c))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(Collectors.counting(), Long::intValue )))
                    .entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            this.setHandScore();
        }

        public void setHandScore() {
            this.handScore = this.cards.chars()
                    .mapToObj(x -> ("00" + cardScores.get((char)x)).substring(("00" + cardScores.get((char)x)).length()-2))
                    .collect(Collectors.joining());
        }

        public int getHandType() { return this.handType; }
        public String getHandScore() { return this.handScore; }
    }

}
