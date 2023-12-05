package foster.terry.aoc2023;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 {
    private final List<String> calibrations = new ArrayList<>();

    private final HashMap<String, Integer> numbers = new HashMap<>();
    public Day01(final List<String> input)
    {
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
        numbers.put("1",1);
        numbers.put("2",2);
        numbers.put("3",3);
        numbers.put("4",4);
        numbers.put("5",5);
        numbers.put("6",6);
        numbers.put("7",7);
        numbers.put("8",8);
        numbers.put("9",9);

        calibrations.addAll(input);
    }

    public int totalDigitCalibrations() {
        final List<Integer> digits = new ArrayList<>();
        for (String line : calibrations) {
            digits.add(Integer.parseInt(findFirstDigit(line) + findLastDigit(line)));
        }
        return digits.stream().mapToInt(v -> v).sum();
    }
    public int totalCalibrations() {
        final List<Integer> digits = new ArrayList<>();
        for (String line : calibrations) {
            //Get first digit
            int fSpellNumberIndex = 1000;
            int firstNumber = 0;
            for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
                int fIndex = line.indexOf(entry.getKey());
                if (fIndex > -1 && fIndex < fSpellNumberIndex) {
                    firstNumber = entry.getValue();
                    fSpellNumberIndex = fIndex;
                }
            }

            //Get last digit
            int lSpellNumberIndex = 0;
            int lastNumber = 0;
            for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
                int lIndex = line.lastIndexOf(entry.getKey());
                if (lIndex > lSpellNumberIndex) {
                    lastNumber = entry.getValue();
                    lSpellNumberIndex = lIndex;
                }
            }

            //Add number to list
            digits.add(firstNumber * 10 + lastNumber);
        }
        return digits.stream().mapToInt(v -> v).sum();
    }

    private String findFirstDigit(String calibration) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(calibration);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
    private String findLastDigit(String calibration) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(new StringBuilder(calibration).reverse());
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

}
