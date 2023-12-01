package foster.terry.aoc2023;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 {
    private final List<String> calibrations = new ArrayList<>();

    private final HashMap<String, String> spelledNumbers = new HashMap<>();
    public Day01(final List<String> input)
    {
        spelledNumbers.put("1", "one");
        spelledNumbers.put("2", "two");
        spelledNumbers.put("3", "three");
        spelledNumbers.put("4", "four");
        spelledNumbers.put("5", "five");
        spelledNumbers.put("6", "six");
        spelledNumbers.put("7", "seven");
        spelledNumbers.put("8", "eight");
        spelledNumbers.put("9", "nine");

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
            String firstNumber = "";
            for (Map.Entry<String, String> entry : spelledNumbers.entrySet()) {
                int fIndex = line.indexOf(entry.getValue());
                if (fIndex > -1 && fIndex < fSpellNumberIndex) {
                    firstNumber = entry.getKey();
                    fSpellNumberIndex = fIndex;
                }
            }
            int fDigitIndex = line.indexOf(findFirstDigit(line));
            fDigitIndex = fDigitIndex < 0 ? 1000 : fDigitIndex;
            if (fDigitIndex < fSpellNumberIndex) {
                firstNumber = findFirstDigit(line);
            }

            //Get last digit
            int lSpellNumberIndex = 0;
            String lastNumber = "";
            for (Map.Entry<String, String> entry : spelledNumbers.entrySet()) {
                int lIndex = line.lastIndexOf(entry.getValue());
                if (lIndex > lSpellNumberIndex) {
                    lastNumber = entry.getKey();
                    lSpellNumberIndex = lIndex;
                }
            }
            int lDigitIndex = line.lastIndexOf(findLastDigit(line));
            lDigitIndex = lDigitIndex >= line.length() ? 0 : lDigitIndex;
            if (lDigitIndex > lSpellNumberIndex || Objects.equals(lastNumber, "")) {
                lastNumber = findLastDigit(line);
            }

            //Add number to list
            digits.add(Integer.parseInt(firstNumber + lastNumber));
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
