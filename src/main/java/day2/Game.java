package day2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private static final Logger LOG = LoggerFactory.getLogger(Game.class);
    final int id;
    List<Draw> draws;
    public Game(String[] sa) {
        id = Integer.parseInt(sa[0].substring(4));
        draws = new ArrayList<>();
        for (int i = 1; i < sa.length; i++) {
            Draw draw = Draw.from(sa[i]);
            LOG.debug(draw.toString());
            draws.add(draw);
        }
    }

    public int getId() {
        return id;
    }

    boolean wasDrawPossible(Draw test) {
        for (Draw d : draws) {
            if (d.r > test.r || d.g > test.g || d.b > test.b) {
                return false;
            }
        }
        return true;
    }

    public Draw maxOfEachColor() {
        int r = draws.stream().mapToInt(Draw::r).max().getAsInt();
        int g = draws.stream().mapToInt(Draw::g).max().getAsInt();
        int b = draws.stream().mapToInt(Draw::b).max().getAsInt();
        return new Draw(r, g, b);
    }

    public record Draw(int r, int g, int b) {
        public static Draw from(String draw) {
            Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");
            Matcher matcher = pattern.matcher(draw);
            int r = 0, g = 0, b = 0;
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);
                switch (color) {
                    case "red" -> r = number;
                    case "green" -> g = number;
                    case "blue" -> b = number;
                }
            }
            return new Draw(r, g, b);
        }
    }
}


