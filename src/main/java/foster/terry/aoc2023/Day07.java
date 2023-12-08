package foster.terry.aoc2023;


import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 {
    List<Hand> hands = new ArrayList<>();
    Map<Character, Integer> cardScores = new HashMap<>();

    List<String> input;

    public Day07 (List<String> puzzleInput) {
        input = puzzleInput;
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
    }

    public long totalWinnings() {
        for (String line : input) {
            String[] play = line.split(" ");
            Hand hand = new Hand(play[0], Integer.parseInt(play[1]));
            Pair<Hand, HashMap<Integer, Integer>> result = getHandScores(hand);
            hand = result.getLeft();
            HashMap<Integer, Integer> map = result.getRight();
            map = sortSets(map);
            hand.handType = getHandType((int)map.values().toArray()[0], map.values().size() == 1 ? 0 : (int)map.values().toArray()[1]);
            hands.add(hand);
        }
        //Sort hands
        sortHands();

        return getTotalWinnings();
    }


    public long totalWinningsJoker() {
        cardScores.replace('J',1);

        for (String line : input) {
            String[] play = line.split(" ");
            Hand hand = new Hand(play[0], Integer.parseInt(play[1]));
            Pair<Hand, HashMap<Integer, Integer>> result = getHandScores(hand);
            hand = result.getLeft();
            HashMap<Integer, Integer> map = result.getRight();

            int jokerCount = map.getOrDefault(1, 0);
            map = sortSets(map);
            map = filterJokers(map);
            hand.handType = getHandType((map.values().isEmpty() ? 0 : (int)map.values().toArray()[0]) + jokerCount, map.values().size() <= 1 ? 0 : (int)map.values().toArray()[1]);
            hands.add(hand);
        }
        sortHands();

        return getTotalWinnings();
    }

    private Pair<Hand, HashMap<Integer, Integer>> getHandScores(Hand hand) {
        char[] cards = hand.cards.toCharArray();
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder handScore = new StringBuilder();
        for(char card : cards) {
            String score = "00" + cardScores.get(card);
            handScore.append(score.substring(Math.max(score.length() - 2, 0)));

            if (map.containsKey(cardScores.get(card))) {
                map.put(cardScores.get(card), map.get(cardScores.get(card)) + 1);
            }
            else {
                map.put(cardScores.get(card), 1);
            }
        }
        hand.handScore = handScore.toString();

        return Pair.of(hand, map);
    }

    private long getTotalWinnings() {
        long totalWinnings = 0;
        for(int x = 0; x < hands.size(); x++) {
            totalWinnings += (long) (x + 1) * hands.get(x).bid;
        }

        return totalWinnings;
    }
    private HashMap<Integer, Integer> sortSets(HashMap<Integer, Integer> map) {
        return map.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed().thenComparing(Map.Entry.<Integer, Integer>comparingByKey().reversed())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
    private HashMap<Integer, Integer> filterJokers(HashMap<Integer, Integer> map) {
        return map.entrySet().stream().filter(x -> x.getKey() != 1).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
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
        hands = hands.stream().sorted(Comparator.comparing(Hand::getHandType).reversed().thenComparing(Hand::getHandScore)).toList();
    }

    private static class Hand {
        public String cards;
        public int bid;
        public int handType;
        public String handScore;

        public Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
        }

        public int getHandType() {
            return this.handType;
        }
        public String getHandScore() {
            return this.handScore;

        }
    }

}
