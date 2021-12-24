package com.company.puzzles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Advent of Code Day 1: Sonar Sweep
 */
public class Day1 {

    public static void main(String[] args) {
        String filename = "/Users/ololadebadmus/Documents/input1.txt";
        List<String> depthList = Collections.emptyList();
        try {
            depthList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Day1 sweep = new Day1();
        System.out.println(sweep.depthMeasurement(depthList));
        System.out.println(sweep.threeMeasurementDepth(depthList));
    }

    public int threeMeasurementDepth(List<String> depthReport) {
        int incrementCount = 0;
        int i = 0, j = i + 1;
        while(i < depthReport.size() - 3) {
            int previous = Integer.parseInt(depthReport.get(i)) + Integer.parseInt(depthReport.get(i + 1))
                    + Integer.parseInt(depthReport.get(i + 2));

            int current =  Integer.parseInt(depthReport.get(i + 1)) + Integer.parseInt(depthReport.get(i + 2))
                    + Integer.parseInt(depthReport.get(i + 3));

            if(previous - current < 0) {
                incrementCount++;
            }
            i++;
        }
        return incrementCount;
    }

    public int depthMeasurement(List<String> depthReport) {
        int incrementCount = 0;
        for (int i = 0; i < depthReport.size() - 1; i++) {
            if(Integer.parseInt( depthReport.get(i)) - Integer.parseInt(depthReport.get(i + 1)) < 0) {
                incrementCount++;
            }
        }
        return incrementCount;
    }
}
