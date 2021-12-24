package com.company.puzzles;


import java.util.*;

/**
 * Advent of Code Day 3: Binary Diagnostic
 */
public class Day3 {
    public static void main(String[] args) {

        String filename = "/Users/ololadebadmus/Documents/day3-input.txt";

        List<String> data = PuzzleUtil.getData(filename);
        Day3 binaryDiag = new Day3();

        System.out.println(binaryDiag.verifyPowerConsumption(data));
        System.out.println(binaryDiag.verifyLifeSupportRating(data));
    }

    public long verifyPowerConsumption(List<String> numbers) {
        List<List<Integer>> dataList =  new ArrayList<>();

        //get data in structure
        for(String number : numbers) {
            for(int i = 0; i < number.length(); i++) {
                int bit = number.charAt(i) - 48;
                if(dataList.isEmpty() || dataList.size() < i + 1) {
                    dataList.add(i, new ArrayList<>());
                    dataList.get(i).add(0);
                    dataList.get(i).add(0);
                }
                List<Integer> list = dataList.get(i);
                list.set(bit, list.get(bit) + 1);
            }
        }

        StringBuilder gammaRateBinary = new StringBuilder(); //most common bits
        StringBuilder epsilonRateBinary = new StringBuilder(); //least common bits
        for(List<Integer> data : dataList) {
            int count0 = data.get(0);
            int count1 = data.get(1);
            gammaRateBinary.append(count0 > count1 ? 0 : 1);
            epsilonRateBinary.append(count0 < count1 ? 0 : 1);
        }

        int gammaRate = Integer.parseInt(gammaRateBinary.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateBinary.toString(), 2);
        return (long) gammaRate * epsilonRate;
    }

    public long verifyLifeSupportRating(List<String> data) {
        int oxygenRating = findOxygenRating(data);
        int scrubberRating = findScrubberRating(data);

        return (long) oxygenRating * scrubberRating;
    }

    private int findOxygenRating(List<String> data) {
        List<String> ratings = data;
        int i = 0;
        while(ratings != null && ratings.size() > 1) {
            ratings = findRatingValue(i, ratings, RatingType.OXYGEN);
            i++;
        }

        assert ratings != null;
        return Integer.parseInt(ratings.get(0), 2);
    }

    private int findScrubberRating(List<String> data) {
        List<String> ratings = data;
        int i = 0;
        while(ratings != null && ratings.size() > 1) {
            ratings = findRatingValue(i, ratings, RatingType.SCRUBBER);
            i++;
        }

        assert ratings != null;
        return Integer.parseInt(ratings.get(0), 2);
    }

    private List<String> findRatingValue(int i, List<String> ratings, RatingType ratingType) {
        List<String> listOfOnes = new ArrayList<>();
        List<String> listOfZeros = new ArrayList<>();

        if(ratings.get(0).length() < i) {
            return null;
        }

        for(String number : ratings) {
            int bit = number.charAt(i) - 48;
            if(bit == 0) {
                listOfZeros.add(number);
            } else if(bit == 1) {
                listOfOnes.add(number);
            }
        }

        int numberOfOnes = listOfOnes.size();
        int numberOfZeros = listOfZeros.size();
        if(ratingType == RatingType.OXYGEN) {
            if(numberOfOnes == numberOfZeros) {
                return listOfOnes;
            } else {
                return numberOfOnes > numberOfZeros ? listOfOnes : listOfZeros;
            }
        } else if(ratingType == RatingType.SCRUBBER) {
            if(numberOfOnes == numberOfZeros) {
                return listOfZeros;
            } else {
                return numberOfOnes < numberOfZeros ? listOfOnes : listOfZeros;
            }
        }
        return  null;
    }

    enum RatingType {
        OXYGEN,
        SCRUBBER
    }
}
