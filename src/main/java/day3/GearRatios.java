package day3;

import common.InputReader;
import common.Pair;
import day2.ColorCubesGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GearRatios {
    private static final Logger LOG = LoggerFactory.getLogger(ColorCubesGame.class);

    public static void main(String[] args) {
        String input = "test.txt";
        System.out.printf("Day 3 part 1 for %s: %d (expected %d)\n", input, answerPart1(input), 4361);
        System.out.printf("Day 3 part 2 for %s: %d (expected %d)\n", input, answerPart2(input), 467835);
        input = "input.txt";
        System.out.printf("Day 3 part 1 for %s: %d (expected %d)\n", input, answerPart1(input), 498559);
        System.out.printf("Day 3 part 2 for %s: %d (expected %d)\n", input, answerPart2(input), 72246648);
    }

    static int answerPart1(String input) {
        var lines = InputReader.readAllLines(input);
        Engine engine = Engine.loadSchematics(lines);
        engine.findAllSymbolsAndParts();
        var parts = engine.findParts();
        //System.out.println(engine.toDebugString(parts));
        var answer = parts.stream().mapToInt(Engine.Part::value).sum();
        return answer;
    }

    static int answerPart2(String input) {
        var lines = InputReader.readAllLines(input);
        Engine engine = Engine.loadSchematics(lines);
        engine.findAllSymbolsAndParts();
        var parts = engine.findGears();
        var answer = parts.stream()
                .mapToInt(p -> p._1().value()*p._2().value()).sum();
        return answer;
    }
}
