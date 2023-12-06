package com.pekelund.aoc;

import com.pekelund.aoc.days.Day1;
import com.pekelund.aoc.days.Day2;
import com.pekelund.aoc.days.Day3;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class Aoc {
    public static void main( String[] args ) throws URISyntaxException, IOException {
        System.out.println( "Hello AoC!" );
        Day1 d1 = new Day1();
        d1.solution1();
        d1.solution2();

        Day2 d2 = new Day2();
        d2.solution1();
        d2.solution2();

        Day3 d3 = new Day3();
        d3.solution1();
        d3.solution2();
    }
}
