package day2;

import common.InputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ColorCubesGame {
    private static final Logger LOG = LoggerFactory.getLogger(ColorCubesGame.class);

    public static void main(String[] args) throws Exception {
        String test = "test.txt";
        var testGames = readGames(test);
        String input = "input.txt";
        var games = readGames(input);

        System.out.printf("Day 2 part 1 for %s: %d (expected %d)\n", test, answerPart1(testGames), 8);
        System.out.printf("Day 2 part 1 for %s: %d (expected %d)\n", input, answerPart1(games), 2076);

        System.out.printf("Day 2 part 2 for %s: %d (expected %d)\n", test, answerPart2(testGames), 2286);
        System.out.printf("Day 2 part 2 for %s: %d (expected %d)\n", input, answerPart2(games), 70950);
    }

    static int answerPart1(List<Game> games) {
        Game.Draw test = new Game.Draw(12, 13, 14);
        var sum = games.stream().filter(game -> game.wasDrawPossible(test)).mapToInt(Game::getId).sum();
        return sum;
    }

    static int answerPart2(List<Game> games) {
        // @formatter:off
        var sum = games.stream()
                .map(g -> g.maxOfEachColor()).peek(g -> LOG.debug(g.toString()))
                .mapToInt(d -> d.r() * d.g() * d.b()).peek(p -> LOG.debug("Power={}", p))
                .sum();
        // @formatter:on
        return sum;
    }

    private static List<Game> readGames(String input) {
        List<String> lines = InputReader.readAllLines(input);
        // @formatter:off
        var games = lines.stream()
                .map(s -> s.replaceAll(" ", ""))
                .map(line -> line.split(":|;")).peek(sa -> LOG.debug(Arrays.toString(sa) + " " + sa.length))
                .map(sa -> new Game(sa))
                .toList();
        // @formatter:on
        return games;
    }
}
