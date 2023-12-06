package com.pekelund.aoc.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.*;

public class Day1 {

    public void solution1() throws URISyntaxException, IOException {

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day1/input1").toURI());

        try (Stream<String> lines = Files.lines(path)) {
            int sum = lines.mapToInt(s -> {
                Matcher matcher = Pattern.compile("^([a-zA-Z]*)(\\d)").matcher(s);
                matcher.find();
                int firstDigit = Integer.parseInt(matcher.group(matcher.groupCount()));

                Matcher matcher2 = Pattern.compile("(\\d)([a-zA-Z]*)$").matcher(s);
                matcher2.find();
                int lastDigit = Integer.parseInt(matcher2.group(matcher2.groupCount() - 1));

                return 10 * firstDigit + lastDigit;
            }).sum();

            System.out.println("Day 1, solution 1: " + sum);
        }
    }

    private static final String[] nums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public void solution2() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day1/input1").toURI());

        try (Stream<String> lines = Files.lines(path)) {
            int sum = lines.mapToInt(s -> {
                TreeMap<Integer, Integer> numberMap = new TreeMap<>();

                for (int t = 0; t < s.length(); t++) {
                    String sub = s.substring(t);

                    if (Character.isDigit(sub.charAt(0))) {
                        numberMap.put(t, Character.getNumericValue(sub.charAt(0)));
                        continue;
                    }
                    for (String st : nums) {
                        if (sub.startsWith(st)) {
                            numberMap.put(t, stringToInteger(st));
                            break;
                        }
                    }

                }
                int firstDigit = numberMap.firstEntry().getValue();
                int lastDigit = numberMap.lastEntry().getValue();

                return 10 * firstDigit + lastDigit;
            }).sum();
            System.out.println("Day 1, solution 2: " + sum);
        }
    }

    private int stringToInteger(String firstNumber) {
        try {
            return Integer.valueOf(firstNumber);
        } catch (NumberFormatException nfe) {
            switch (firstNumber) {
                case "one":
                    return 1;
                case "two":
                    return 2;
                case "three":
                    return 3;
                case "four":
                    return 4;
                case "five":
                    return 5;
                case "six":
                    return 6;
                case "seven":
                    return 7;
                case "eight":
                    return 8;
                case "nine":
                    return 9;
                default:
                    return 0;
            }
        }

    }
}