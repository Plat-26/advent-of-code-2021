package com.company.puzzles;

import java.util.Collections;
import java.util.List;

/**
 * Advent of Code Day 7: The Treachery Of Whales
 */
public class Day7 {

    public static void main(String[] args) {
        String dataAsString = PuzzleUtil.getDataAsString("src\\com\\company\\input\\day7-input.txt");
        List<Integer> input = PuzzleUtil.convertToListOfInts(dataAsString.trim().split(","));


        Day7 treachery = new Day7();
        System.out.println(treachery.escape1(input));
        System.out.println(treachery.escape2(input));
    }

    /**
     * When escape fuel is one unit per step
     * @param crabs
     * @return
     */
    private long escape1(List<Integer> crabs) {
        int medianLocation = this.sortInputFindMedian(crabs);

        return crabs.stream() //calculate fuel cost
                .mapToInt(loc -> Math.abs(loc - medianLocation))
                .sum();
    }

    /**
     * When escape fuel increases by one unit with every step
     * @param crabs
     * @return
     */
    private long escape2(List<Integer> crabs) {
        int sum = crabs.stream()
                .mapToInt(c -> c).sum();
        int avg = sum / crabs.size();

        return crabs.stream()
                .mapToLong(c -> this.sumOfPoints(Math.abs(c - avg)))
                .sum();

    }

    private long sumOfPoints(int point) {
        int x = 0;
        while(point > 0) {
            x += point;
            point--;
        }

        return x;
    }

    private int sortInputFindMedian(List<Integer> crabLocations) {
        Collections.sort(crabLocations); //sort array

        int median = crabLocations.size() / 2; //find middle point
        return crabLocations.get(median); //return location at median
    }
}
