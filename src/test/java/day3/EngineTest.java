package day3;

import common.InputReader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class EngineTest {

    private static List<Integer> countNumbers(char[] lines) {
        List<Integer> numbers = new ArrayList<>();
        String inputString = new String(lines);
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(inputString);

        while (numberMatcher.find()) {
            numbers.add(Integer.parseInt(numberMatcher.group(0)));
        }
        return numbers;
    }

    private static int countSymbols(char[] lines) {
        String inputString = new String(lines);
        // Regex to count symbols excluding dots
        Pattern symbolPattern = Pattern.compile("[^\\w\\d.]+");
        Matcher symbolMatcher = symbolPattern.matcher(inputString);

        int symbolCount = 0;
        while (symbolMatcher.find()) {
            symbolCount++;
        }
        return symbolCount;
    }

    @Test
    void parseLineForParts() {
        char[] lines = "467..114".toCharArray();
        var results = Engine.parseLineForSymbolsAndParts(lines, 0);
        var parts = results._1();
        assertEquals(2, parts.size(), "Two parts should be found");
        var symbols = results._2();
        assertEquals(0, symbols.size(), "No symbols should be found");
        Engine.Part part1 = parts.get(0);
        assertEquals(new Engine.Part(0, 0, 2, 0, 467), part1);
        Engine.Part part2 = parts.get(1);
        assertEquals(new Engine.Part(5, 0, 7, 0, 114), part2);
    }

    @Test
    void parseLineForSymbols() {
        char[] lines = "...$.*....".toCharArray();
        var results = Engine.parseLineForSymbolsAndParts(lines, 0);
        var parts = results._1();
        assertEquals(0, parts.size(), "No parts should be found");
        var symbols = results._2();
        assertEquals(2, symbols.size(), "2 symbols should be found");
        var symbol1 = symbols.get(0);
        assertEquals(new Engine.Symbol(3, 0, '$'), symbol1);
        var symbol2 = symbols.get(1);
        assertEquals(new Engine.Symbol(5, 0, '*'), symbol2);
    }

    @Test
    void parseLineForSymbolsAndNumbers() {
        char[] lines = ".617*.....".toCharArray();
        var results = Engine.parseLineForSymbolsAndParts(lines, 1);
        var parts = results._1();
        assertEquals(1, parts.size(), "One parts should be found");
        var part1 = parts.get(0);
        assertEquals(new Engine.Part(1, 1, 3, 1, 617), part1);
        var symbols = results._2();
        assertEquals(1, symbols.size(), "One symbols should be found");
        var symbol1 = symbols.get(0);
        assertEquals(new Engine.Symbol(4, 1, '*'), symbol1);
    }



    @Test
    void testAllRealInput() {
        var lines = InputReader.readAllLines("input.txt");
        int i=0;
        for(String line : lines) {
            testRealInput(line, i++);
        }
    }

    void testRealInput(String input, int index) {
        char[] lines = input.toCharArray();
        var results = Engine.parseLineForSymbolsAndParts(lines, 1);
        var parts = results._1();
        var expectedNumbers = countNumbers(lines);
        assertEquals(expectedNumbers.size(), parts.size(), "# parts should be found on line:"+index+"\n"+input+"\n"+parts);
        var numbers = parts.stream().map(Engine.Part::value).toList();
        assertIterableEquals(expectedNumbers, numbers);
        var symbols = results._2();
        assertEquals(countSymbols(lines), symbols.size(), "# symbols should be on line:"+index+"\n"+input+"\n"+symbols);
    }

}