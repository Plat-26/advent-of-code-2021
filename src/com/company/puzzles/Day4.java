package com.company.puzzles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Advent of Code 2021 Day 4: Giant Squid
 */
public class Day4 {

    public static void main(String[] args) {
        String numsFile = "src\\com\\company\\input\\day4-input-nums.txt";
        String arraysFile = "src\\com\\company\\input\\day4-input-array.txt";

        Day4 giantSquid = new Day4();
        List<int[][]> bingoBoards = PuzzleUtil.getBingoArray(arraysFile);
        String[] bingoNumbers = giantSquid.getBingoNumbers(numsFile);
        System.out.println(giantSquid.playBingo(bingoBoards, bingoNumbers));
        System.out.println(giantSquid.makeSquidWin(bingoBoards, bingoNumbers));
    }

    private long playBingo(List<int[][]> bingoBoards, String[] bingoNumbers) {
        for(String num : bingoNumbers) {
            int number = Integer.parseInt(num);
            markNumberOnBoards(number, bingoBoards);

            int[][] winningBoard = findWinningBoard(bingoBoards);
            if(winningBoard != null) {
                return calculateFinalScore(number, winningBoard);
            }
        }
        return 0;
    }

    private long makeSquidWin(List<int[][]> bingoBoards, String[] bingoNumbers) {
        List<Long> resultSet = new ArrayList<>();
        HashSet<int[][]> uniqueBoards = new HashSet<>();

        for (String bingoNumber : bingoNumbers) {
            int number = Integer.parseInt(bingoNumber);
            markNumberOnBoards(number, bingoBoards);

            findAllWinningBoards(number, bingoBoards, resultSet, uniqueBoards);
        }
        return resultSet.get(resultSet.size() - 1);
    }

    private long calculateFinalScore(int number, int[][] winningBoard) {
        int sum = 0;
        for(int[] ints : winningBoard) {
            for(int j = 0; j < winningBoard.length; j++) {
                if(ints[j] != -1) {
                    sum += ints[j];
                }
            }
        }
        return number * sum;
    }

    private void findAllWinningBoards(int number, List<int[][]> bingoBoards, List<Long> resultSet, HashSet<int[][]> uniqueBoards) {
        for(int[][] board : bingoBoards) {
            if(uniqueBoards.contains(board)) { //to eliminate repeat checks
                continue;
            }

            //check rows
            int i = 0;
            while(i < board.length) {
                int val = -1;
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] != val) {
                        break;
                    }
                    if(j == board[i].length - 1 && board[i][j] == val) {

                        if(!uniqueBoards.contains(board)) {
                            uniqueBoards.add(board);
                            resultSet.add(calculateFinalScore(number, board));
                        }
                    }
                }
                i++;
            }

            //check columns
            int k = 0;
            while(k < board.length) {
                int val = -1;
                for(int j = 0; j < board[k].length; j++) {
                    if(board[j][k] != val) {
                        break;
                    }

                    if(j == board[k].length - 1 && board[j][k] == val) {
                        if(!uniqueBoards.contains(board)) {
                            uniqueBoards.add(board);
                            resultSet.add(calculateFinalScore(number, board));
                        }
                    }
                }
                k++;
            }
        }
    }

    private int[][] findWinningBoard(List<int[][]> bingoBoards) {
        for(int[][] board : bingoBoards) {

            //check rows
            int i = 0;
            while(i < board.length) {
                int val = -1;
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] != val) {
                        break;
                    }
                    if(j == board[i].length - 1 && board[i][j] == val) {
                        return board;
                    }
                }
                i++;
            }

            //check columns
            int k = 0;
            while(k < board.length) {
                int val = -1;
                for(int j = 0; j < board[k].length; j++) {
                    if(board[j][k] != val) {
                        break;
                    }

                    if(j == board[k].length - 1 && board[j][k] == val) {
                        return board;
                    }
                }
                k++;
            }
        }
        return null;
    }

    private void markNumberOnBoards(int number, List<int[][]> bingoBoards) {
        for(int[][] board : bingoBoards) {

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] == number) {
                        board[i][j] = -1;
                    }
                }
            }
        }
    }


    private String[] getBingoNumbers(String filename) {
        String dataAsString = PuzzleUtil.getDataAsString(filename);

        return dataAsString.split(",");
    }
}
