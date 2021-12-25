package com.company.puzzles;

import java.util.List;

/**
 * Advent of Code 2021 Day 8: Seven Segment Search
 */
public class Day8 {

    public static void main(String[] args) {
        String filename = "src\\com\\company\\input\\day8-input.txt";
        List<String> input = PuzzleUtil.getSevenSegmentDigits(filename);

        List<String[]> data = PuzzleUtil.getSegments(filename);
        Day8 sevenSegmentSearch = new Day8();
        System.out.println(sevenSegmentSearch.findUniqueNumbers(input));
        System.out.println(sevenSegmentSearch.findSumOfDigits(data));
    }

    private int findUniqueNumbers(List<String> outputDigits) {
        int count = 0;

        for(String digit : outputDigits) {
            int len = digit.length();
            if(len == 2 || len == 3 || len == 4 || len == 7) {
                count++;
            }
        }
        return count;
    }

    private long findSumOfDigits(List<String[]> outputValues) {
        long sum = 0;

        for(String[] output : outputValues) {
            String[] segments = findSegmentPattern(output[0].split(" "));
            String[] digits = output[1].split(" ");
            int val = 0;
            for(String digit: digits) {
                for (int i = 0; i < segments.length; i++) {
                    String seg = sortString(segments[i]);

                    if(digit.length() == seg.length()) {
                        if(seg.equals(sortString(digit))) {
                            val = (val * 10) + i;
                        }
                    }
                }
            }
            sum += val;
        }
        return sum;
    }

    private String[] findSegmentPattern(String[] data) {
        String[] segments = new String[10];

        for(String str : data) {
            if(str.length() == 2) {
                segments[1] = str;
            } else if(str.length() == 3) {
                segments[7] = str;
            } else if(str.length() == 4) {
                segments[4] = str;
            } else if(str.length() == 7) {
                segments[8] = str;
            }
        }

        for(String str : data) {
            if(str.length() == 6) {
                if (contains(str, segments[4])) {
                    segments[9] = str;
                }
            }
        }

        for(String str : data) {
            if(str.length() == 5) {
                if(contains(str, segments[7])) {
                    segments[3] = str;
                    continue;
                }
                if(contains(segments[9], str)) {
                    segments[5] = str;
                    continue;
                }
            }
            if(str.length() == 6) {
                if( contains(str, segments[1]) && !contains(str, segments[9])) { //
                    segments[0] = str;
                }
            }
        }

        for(String str : data) {
            if(str.length() == 5) {
                if(!str.equals(segments[3]) && !str.equals(segments[5])) {
                    if(!contains(str, segments[5])) {
                        segments[2] = str;
                    }
                }
            }

            if(str.length() == 6) {
                if(!str.equals(segments[9]) && !str.equals(segments[0])) {
                    segments[6] = str;
                }
            }
        }
        return segments;
    }

    private static boolean contains(String input1, String input2) {
        char[] chars = input2.toCharArray();
        for(char ch : chars) {
            if(!input1.contains(Character.toString(ch))) {
                return false;
            }
        }
        return true;
    }

    private static String sortString(String str) {
        char[] arry = str.toCharArray();

        for(int i = 0; i < arry.length; i++) {
            for(int j = i + 1; j < arry.length; j++) {
                if(arry[i] > arry[j]) {
                    char temp = arry[i];
                    arry[i] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        return new String(arry);
    }
}
