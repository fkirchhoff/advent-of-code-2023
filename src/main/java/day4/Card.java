package day4;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {
    HashSet<Integer> winningNumbers = new HashSet<>();
    HashSet<Integer> cardNumbers = new HashSet<>();

    int cardNumber;

    int numberOfWinningNumbers;

    public Card(String line) {
        String[] parts = line.split(":|\\|");
        cardNumber = Integer.parseInt(parts[0].substring(5).strip());
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(parts[1]);
        while (numberMatcher.find()) {
            int number = Integer.parseInt(numberMatcher.group(0));
            winningNumbers.add(number);
        }
        numberMatcher = numberPattern.matcher(parts[2]);
        while (numberMatcher.find()) {
            int number = Integer.parseInt(numberMatcher.group(0));
            cardNumbers.add(number);
        }
        numberOfWinningNumbers = winningNumbers();
    }

    public int winningNumbers() {
        Set<Integer> winners = new HashSet<Integer>(winningNumbers);
        winners.retainAll(cardNumbers);
        return winners.size();
    }

    public int cardScore() {
        Set<Integer> winners = new HashSet<>(winningNumbers);
        winners.retainAll(cardNumbers);
        if (winners.size() > 0) {
            int score = (int) Math.pow(2, winners.size() - 1);
            return score;
        } else {
            return 0;
        }
    }
}
