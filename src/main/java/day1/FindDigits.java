package day1;

import common.InputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FindDigits {
    private static final Logger LOG = LoggerFactory.getLogger(FindDigits.class);
    static List<String> digitWords = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    static List<String> digits = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

    public static void main(String[] args) throws Exception {
        String input = "test.txt";
        System.out.printf("Day1 part 1 for %s: %d (expected %d)\n", input, answer(input, false), 142);
        input = "input.txt";
        System.out.printf("Day1 part 1 for %s: %d (expected %d)\n", input, answer(input, false), 56465);

        input = "test2.txt";
        System.out.printf("Day1 part 2 for %s: %d (expected %d)\n", input, answer(input, true), 281);
        LOG.debug("=".repeat(10));
        input = "input.txt";
        System.out.printf("Day1 part 2 for %s: %d (expected %d)\n", input, answer(input, true), 55902);
    }

    static int answer(String input, boolean findWords) throws Exception {
        List<String> lines = InputReader.readAllLines(input);
        int score = lines.stream()
                .peek(s -> LOG.debug(s))
                .map(s -> FindDigits.scanForDigits(s, findWords))
                .peek(s -> LOG.debug("=====>" + s))
                .map(s -> s.get(0) + "" + s.get(s.size() - 1))
                .peek(s -> LOG.debug("=====>" + s))
                .mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        return score;
    }

    public static List<String> scanForDigits(String line, boolean findWords) {
        var all = new ArrayList<String>();
        all.addAll(digits);
        if (findWords) all.addAll(digitWords);
        List<String> digitsFound = new ArrayList<>();
        for (int from = 0; from < line.length(); from++) {
            for (String digit : all) {
                if (from + digit.length() > line.length()) continue;
                String test = line.substring(from, from + digit.length());
                if (digit.equals(test)) {
                    digitsFound.add(digitWordToDigit(digit));
                    break;
                }
            }
        }
        return digitsFound;
    }

    static String digitWordToDigit(String word) {
        int index = digitWords.indexOf(word);
        if (index >= 0) {
            return digits.get(index);
        } else {
            return word;
        }
    }
}
