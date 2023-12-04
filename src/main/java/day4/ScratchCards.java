package day4;

import common.InputReader;
import day2.ColorCubesGame;
import day3.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ScratchCards {
    private static final Logger LOG = LoggerFactory.getLogger(ColorCubesGame.class);

    public static void main(String[] args) {
        String input = "test.txt";
        System.out.printf("Day 4 part 1 for %s: %d (expected %d)\n", input, answerPart1(input), 13);
        System.out.printf("Day 4 part 2 for %s: %d (expected %d)\n", input, answerPart2(input), 30);
        input = "input.txt";
        System.out.printf("Day 4 part 1 for %s: %d (expected %d)\n", input, answerPart1(input), 21568);
        System.out.printf("Day 4 part 2 for %s: %d (expected %d)\n", input, answerPart2(input), 11827296);
    }

    static int answerPart1(String input) {
        var lines = InputReader.readAllLines(input);
        List<Card> cards = lines.stream().map(Card::new).toList();
        int answer = cards.stream()
                .mapToInt(Card::cardScore)
                //.peek(i-> System.out.println(""+i))
                .sum();
        return answer;
    }

    static int answerPart2(String input) {
        var lines = InputReader.readAllLines(input);
        List<Card> cards = lines.stream().map(Card::new).toList();
        int[] tally = new int[cards.size()];
        for(Card card : cards) {
            tally[card.cardNumber-1] = 1;
        }
        for(Card card : cards) {
            int numToAdd = card.numberOfWinningNumbers;
            for(int extra=1; extra <= tally[card.cardNumber-1]; extra++) {
                for (int i = 1; i <= numToAdd; i++) {
                    int k = card.cardNumber - 1 + i;
                    tally[k] += 1;
                }
            }
        }
        return Arrays.stream(tally).sum();
    }
}
