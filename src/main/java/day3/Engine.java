package day3;

import common.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Math.abs;

public class Engine {
    char[][] cells;
    int xmax, ymax;

    List<Part> parts = new ArrayList<>();
    List<Symbol> symbols = new ArrayList<>();
    List<List<Part>> partsByLine = new ArrayList<>();
    List<List<Symbol>> symbolsByLine = new ArrayList<>();

    public Engine(int xmax, int ymax) {
        this.xmax = xmax;
        this.ymax = ymax;
        this.cells = new char[ymax][xmax];

        for (int x = 0; x < xmax; x++) {
            for (int y = 0; y < ymax; y++) {
                cells[y][x] = 0;
            }
        }
    }

    static Engine loadSchematics(List<String> lines) {
        int ymax = lines.size();
        int xmax = lines.get(0).length();
        Engine grid = new Engine(xmax, ymax);
        int x, y = 0;
        for (String line : lines) {
            char[] engineLine = line.toCharArray();
            x = 0;
            for (char cell : engineLine) {
                grid.cells[y][x] = cell;
                x++;
            }
            y++;
        }
        return grid;
    }

    public static Pair<List<Part>, List<Symbol>> parseLineForSymbolsAndPartsOld(char[] line, int y) {
        List<Part> parts = new ArrayList<>();
        List<Symbol> symbols = new ArrayList<>();
        int x1 = 0;
        boolean collect = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length; i++) {
            if (line[i] == '.' || !isDigit(line[i]) || (i == line.length - 1)) {
                if (collect) {
                    collect = false;
                    if (i == (line.length - 1) && isDigit(line[i])) {
                        sb.append(line[i]);
                        parts.add(new Part(x1, y, i, y, Integer.parseInt(sb.toString())));
                    } else {
                        parts.add(new Part(x1, y, i - 1, y, Integer.parseInt(sb.toString())));
                    }
                }
                if (!isDigit(line[i]) && line[i] != '.') {
                    symbols.add(new Symbol(i, y, line[i]));
                }
                continue;
            }
            if (isDigit(line[i])) {
                if (!collect) {
                    collect = true;
                    sb = new StringBuilder();
                    x1 = i;
                }
                sb.append(line[i]);
            }
        }
        return new Pair<>(parts, symbols);
    }

    public static Pair<List<Part>, List<Symbol>> parseLineForSymbolsAndParts(char[] line, int y) {
        List<Part> numbers = new ArrayList<>();
        String inputString = new String(line);
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(inputString);

        while (numberMatcher.find()) {
            int number = Integer.parseInt(numberMatcher.group(0));
            int start = numberMatcher.start();
            int end = numberMatcher.end() - 1 ;
            numbers.add(new Part(start, y, end, y, number));
        }

        List<Symbol> symbols = new ArrayList<>();
        Pattern symbolPattern = Pattern.compile("[^\\w\\d.]{1}");
        Matcher symbolMatcher = symbolPattern.matcher(inputString);

        while (symbolMatcher.find()) {
            char symbol = symbolMatcher.group(0).charAt(0);
            assert symbolMatcher.group(0).length() == 1;
            int start = symbolMatcher.start();
            symbols.add(new Symbol(start, y, symbol));
        }

        return new Pair<>(numbers,symbols);
    }

    void findAllSymbolsAndParts() {
        for (int y = 0; y < ymax; y++) {
            var result = parseLineForSymbolsAndParts(this.cells[y], y);
            parts.addAll(result._1());
            symbols.addAll(result._2());
            partsByLine.add(result._1());
            symbolsByLine.add(result._2());
        }
    }

    List<Part> findParts() {
        List<Part> parts = new ArrayList<>();
        for (Part part : this.parts) {
            for (Symbol symbol : symbols) {
                if (doesSymbolTouchPart(symbol, part)) {
                    parts.add(part);
                    break;
                }
            }
        }
        return parts;
    }

    List<Pair<Part, Part>> findGears() {
        List<Pair<Part, Part>> parts = new ArrayList<>();
        for (Symbol symbol : symbols) {
            if (symbol.value == '*') {
                List<Part> gearParts = new ArrayList<>();
                for (Part part : this.parts) {
                    if (doesSymbolTouchPart(symbol, part)) {
                        gearParts.add(part);
                    }
                }
                if (gearParts.size() == 2) {
                    parts.add(new Pair<>(gearParts.get(0), gearParts.get(1)));
                }
            }
        }
        return parts;
    }

    private boolean doesSymbolTouchPart(Symbol s, Part p) {
        return
                // Above or below the line
                abs(s.y - p.y) <= 1 &&
                        // between inclusive x1-1 and x2+1
                        s.x >= p.x - 1 && s.x <= p.x2 + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < ymax; y++) {
            for (int x = 0; x < xmax; x++) {
                sb.append(cells[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toDebugString(List<Part> realParts) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < ymax; y++) {
//            for (int x = 0; x < xmax; x++) {
//                sb.append(cells[y][x]);
//            }
//            sb.append("\n");
            for (int x = 0; x < xmax; x++) {
                for (Symbol symbol : symbolsByLine.get(y)) {
                    if (symbol.x == x && symbol.y == y) {
                        sb.append("@");
                        break;
                    }
                }
                for (Part p : partsByLine.get(y)) {
                    if (x >= p.x && x <= p.x2) {
                        if (realParts.contains(p)) {
                            sb.append(".");
                        } else {
                            sb.append("X");
                        }
                        break;
                    }
                }
                if (cells[y][x] == '.') {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    record Part(int x, int y, int x2, int y2, int value) {
    }

    record Symbol(int x, int y, char value) {
    }
}
