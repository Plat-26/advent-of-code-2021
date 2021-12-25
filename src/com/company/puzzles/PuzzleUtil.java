package com.company.puzzles;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PuzzleUtil {

    public static List<String> getData(String filename) {
//        String filename = "/Users/ololadebadmus/Documents/day2-input.txt";
        List<String> dataSet = Collections.emptyList();

        try {
            dataSet = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public static String getDataAsString(String filename) {
        String data = "";

        try {
           data = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<int[][]> getBingoArray(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<int[][]> bingoBoards = new ArrayList<>();
        int[][] board = new int[5][5];

        while (scanner.hasNextLine()) {
            for(int i = 0; i < board.length; i++) {
                String line = scanner.nextLine();
                if(line.isEmpty()) {
                    bingoBoards.add(board);
                    board = new int[5][5];
                    break;
                }
                String[] data = line.trim().split(" ");
                int k = 0;
                for(int j = 0; j < board.length;) {
                    if("".equals(data[k])) {
                        k++;
                        continue;
                    }
                    board[i][j] = Integer.parseInt(data[k]);
                    k++; j++;
                }
            }
        }
        return bingoBoards;
    }

    public static List<Integer> convertToListOfInts(String[] arry) {
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, arry);

        return stringList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> getSevenSegmentDigits(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> digits = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            String[] split = input.split("\\| ");

            String[] output = split[1].split(" ");
            Collections.addAll(digits, output);
        }

        return digits;
    }

    /**
     * For day 8: method  to get a list of output values per entry.
     * @param filename
     * @return
     */
    public static List<String[]> getSegmentOutputValues(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> digits = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            String[] split = input.split("\\| ");

            String[] output = split[1].split(" ");
            digits.add(output);
        }

        return digits;
    }

    public static List<String[]> getSegments(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> digits = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            String[] split = input.split("\\| ");

            digits.add(split);
        }

        return digits;
    }
}
