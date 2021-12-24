package com.company.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Advent of Code 20201 Day 6: LanternFish
 */
public class Day6 {

    public static void main(String[] args) {
        String filename = "src\\com\\company\\input\\day6-input.txt";
        Day6 lanternFish = new Day6();

        String[] data = PuzzleUtil.getDataAsString(filename).trim().split(",");
        List<String> dataList = new ArrayList<>();
        Collections.addAll(dataList, data);

        System.out.println(lanternFish.countLanternFish(dataList));
    }

    /**
     * First attempt, time complexity O(n!)
     * @param listOfFish
     * @return
     */
    private long simulateLanternFish(List<String> listOfFish) {
        int days = 0;
        while(days < 80) {
            int currentSize = listOfFish.size();
            for(int i = 0; i < currentSize; i++) {
                int curr = Integer.parseInt(listOfFish.get(i));
                if(curr == 0) {
                    listOfFish.set(i, String.valueOf(6));
                    listOfFish.add(String.valueOf(8));
                } else {
                    listOfFish.set(i, String.valueOf(curr - 1));
                }
            }
            days++;
        }
        return listOfFish.size();
    }

    /**
     * Optimal solution
     * @param listOfFish
     * @return
     */
    private long countLanternFish(List<String> listOfFish) {
        long[] fishArray = new long[9];
        for (String fishLife : listOfFish) {
            fishArray[Integer.parseInt(fishLife)]++;
        }
        System.out.println(listOfFish.size());

        int day = 0;
        while(day < 256) {
            long newBornCount = fishArray[0];
            for(int i = 1; i < fishArray.length; i++) {
                fishArray[i - 1] = fishArray[i];
            }
            fishArray[6] += newBornCount;
            fishArray[8] = newBornCount;
            day++;
            if(day == 80) {
                System.out.println(Arrays.stream(fishArray).sum());
            }
        }
        return Arrays.stream(fishArray).sum();
    }
}
