package com.pekelund.aoc.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Day3 {
    public void solution1() throws URISyntaxException, IOException {

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day3/input").toURI());

        List<Integer> totalPartsList = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            int sum = 0;
            ArrayList<String> collectedLines = lines.collect(Collectors.toCollection(ArrayList::new));

            List<List<String>> linesWithNumbers = new ArrayList<>();

            for (String line : collectedLines) {
                List<String> numbersInLine = getNumbersFromString(line);
                numbersInLine.add(0, line);
                linesWithNumbers.add(numbersInLine);
            }

            for (int index = 0; index < linesWithNumbers.size(); index++) {
                List<String> currentLine = linesWithNumbers.get(index);

                if (currentLine.size() > 1) {
                    totalPartsList.addAll(findNumbersWithAdjacentSymbols(currentLine, index, linesWithNumbers));
                }
            }

            sum = totalPartsList.stream().reduce(0, Integer::sum);
            System.out.println("Day 3, solution 1: " + sum);
        }
    }

    public void solution2() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("day3/input.small").toURI());

        try (Stream<String> lines = Files.lines(path)) {
            int sum = 0;
//            lines.forEach(System.out::println);

            System.out.println("Day 3, solution 2: ");
        }
    }

    private List<Integer> findNumbersWithAdjacentSymbols(List<String> currentLine, int index, List<List<String>> linesWithNumbers) {

        List<Integer> partsNumbers = new ArrayList<>();
        String currentRow = currentLine.get(0);
        int currentPos = 0;
        int talIndex = 1;
        while (talIndex < currentLine.size()) {
            boolean numberAdded = false;
            String currentNumber = currentLine.get(talIndex);
            int talPosStart = currentRow.indexOf(currentNumber, currentPos);
            int talPosEnd = talPosStart + currentNumber.length() - 1;
            currentPos = talPosEnd;

            int endIndex = talPosEnd < currentRow.length() - 1 ? talPosEnd + 2 : talPosEnd + 1;
            int beginIndex = talPosStart > 0 ? talPosStart - 1 : talPosStart;
            if (index > 1) {
                String neighbours = linesWithNumbers.get(index - 1).get(0).substring(beginIndex, endIndex);
                neighbours = neighbours.replaceAll("\\.+", "");
                if (!neighbours.isEmpty()) {
                    partsNumbers.add(Integer.parseInt(currentNumber));
                    numberAdded = true;
                }
            }
            if (!numberAdded && (talPosStart > 0 && currentRow.charAt(talPosStart - 1) != '.')
                    || (talPosEnd < currentRow.length() - 1 && currentRow.charAt(talPosEnd + 1) != '.')) {
                partsNumbers.add(Integer.parseInt(currentNumber));
                numberAdded = true;
            }

            if (!numberAdded && index < linesWithNumbers.size() - 1) {
                String neighbours = linesWithNumbers.get(index + 1).get(0).substring(beginIndex, endIndex);
                neighbours = neighbours.replaceAll("\\.+", "");
                if (!neighbours.isEmpty()) {
                    partsNumbers.add(Integer.parseInt(currentNumber));
                }
            }
            talIndex++;
        }
        return partsNumbers;
    }

    private List<String> getNumbersFromString(String line) {

        Pattern integerPattern = Pattern.compile("\\d+");
        Matcher matcher = integerPattern.matcher(line);

        List<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(matcher.group());
        }
        return integerList;
    }


}