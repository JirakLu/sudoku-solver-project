package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] sud = {
                {0,0,0,0,4,8,0,0,0},
                {1,0,4,0,5,0,0,0,0},
                {0,0,8,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0},
                {0,0,5,0,9,0,3,1,0},
                {7,0,9,0,1,0,5,6,0},
                {3,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,2,3},
                {8,0,6,5,2,0,0,7,0},
        };

        Sudoku sudoku = new Sudoku(sud);


        long start = System.currentTimeMillis();
        sudoku.basicSolve();
        long finish = System.currentTimeMillis();
        System.out.println(finish-start);
    }
}
