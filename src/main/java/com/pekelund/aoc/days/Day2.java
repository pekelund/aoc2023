package com.pekelund.aoc.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2 {

    private static final String[] cubeColors = {"red", "green", "blue"};

    private static final Map<String, Integer> rules = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14);

    static class GameStats {
        private final int id;
        private boolean playable = true;

        private Map<String, Integer> cubesMap = new HashMap<>();

        public GameStats(String s) {
            for (String color : cubeColors) {
                cubesMap.put(color, 0);
            }

            Matcher matcher = Pattern.compile("^Game (\\d+):.*").matcher(s);
            matcher.find();

            this.id = Integer.parseInt(matcher.group(1));
            s = s.substring(s.indexOf(": ") + 2);
            String[] sets = s.split("; ");
            for (String set : sets) {
                String[] colours = set.split(", ");
                for (String colour : colours) {
                    String[] parts = colour.split(" ");

                    cubesMap.replace(parts[1], Math.max(Integer.parseInt(parts[0]), cubesMap.get(parts[1])));
                    if (rules.get(parts[1]) < Integer.parseInt(parts[0])) {
                        playable = false;
                    }
                }
            }
        }

        public int getProduct() {
            int product = 1;
            for (String colour : cubeColors) {
                product *= cubesMap.get(colour);
            }

            return product;
        }
    }
    public void solution1() throws URISyntaxException, IOException {

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day2/input2").toURI());

        try (Stream<String> lines = Files.lines(path)) {
            int sum = lines.map(GameStats::new).filter(gameStats -> gameStats.playable).mapToInt(value -> value.id).sum();

            System.out.println("Day 2, solution 2: " + sum);
        }
    }

    public void solution2() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day2/input2").toURI());

        try (Stream<String> lines = Files.lines(path)) {
            int sum = lines.map(GameStats::new).mapToInt(GameStats::getProduct).sum();
            System.out.println("Day 2, solution 2: " + sum);
        }
    }
}