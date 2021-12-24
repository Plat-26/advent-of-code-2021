package com.company.puzzles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * DAY 2: Dive
 */
public class Day2 {

    public static void main(String[] args) {
        String filename = "/Users/ololadebadmus/Documents/day2-input.txt";
        List<String> directions = Collections.emptyList();

        try {
            directions = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Day2 dive = new Day2();
        System.out.println(dive.determineLocation(directions));
        System.out.println(dive.determineAim(directions));
    }

    public double determineLocation(List<String> directions) {
        double depth = 0;
        double horizontal = 0;
        for(String dir : directions) {
            String[] direction = dir.split(" ");
            int x = Integer.parseInt(direction[1]);

            if(direction[0].contains("forward")) {
                horizontal += x;
            } else if (direction[0].contains("down")) {
                depth += x;
            } else if (direction[0].contains("up")) {
                depth -= Integer.parseInt(direction[1]);
            }
        }
        return depth * horizontal;
    }

    public double determineAim(List<String> directions) {
        double depth = 0;
        double horizontal = 0;
        double aim = 0;
        for(String dir : directions) {
            String[] direction = dir.split(" ");
            int x = Integer.parseInt(direction[1]);

            if(direction[0].contains("forward")) {
                horizontal += x;
                depth += aim * x;
            } else if (direction[0].contains("down")) {
                aim += x;
            } else if (direction[0].contains("up")) {
                aim -= x;
            }
        }

        return depth * horizontal;
    }
}
