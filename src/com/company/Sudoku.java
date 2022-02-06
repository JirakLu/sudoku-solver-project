package com.company;

import java.util.Arrays;

public class Sudoku {

//    Sudoku info
    private final int[][] starterSudoku;
    private int[][] helperSudoku;
    public int[][] solvedSudoku;
//    Sudoku params
    private final int sudokuHeight;
    private final int sudokuWidth;
//    Sudoku graphic
    private SudokuDisplay sudokuDisplay;
    private boolean graphic = false;

    public Sudoku(int[][] sudoku) {
        this.starterSudoku = copy(sudoku);
        this.helperSudoku = copy(sudoku);
        this.sudokuHeight = sudoku.length;
        this.sudokuWidth = sudoku[0].length;
    }

    public boolean basicSolve() {
        for (int row = 0; row < this.sudokuHeight; row++) {
            for (int col = 0; col < this.sudokuWidth; col++) {
                if (this.helperSudoku[row][col] == 0) {
                    for (int number = 0; number <= 9; number++) {
                        if (isValidPlacement(row, col, number)) {
                            this.helperSudoku[row][col] = number;
                            if (this.graphic) this.sudokuDisplay.updateCord(row,col,number);
                            if (basicSolve()) {
                                return true;
                            } else {
                                this.helperSudoku[row][col] = 0;
                                if (this.graphic) this.sudokuDisplay.updateCord(row,col,0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        this.solvedSudoku = copy(this.helperSudoku);
        this.helperSudoku = copy(this.starterSudoku);
        return true;
    }

    public void threadSolve() {
        System.out.println("Thread solve");
    }

    public void fastestSolve() {
        System.out.println("fastest solve");
    }

    enum Solve { basicSolve, threadSolve, fastestSolve}

//    Timed solve
    public long timeSolve(Solve solveType) {
        long start = System.currentTimeMillis();
        switch (solveType) {
            case basicSolve -> this.basicSolve();
            case threadSolve -> this.threadSolve();
            case fastestSolve -> this.fastestSolve();
        }
        long finish = System.currentTimeMillis();
        //    Time elapsed
        long timeElapsed = finish - start;
        System.out.println("Výpočet trval -> " + timeElapsed + "ms.");
        return timeElapsed;
    }

//    Graphic solve
    public void graphicSolve(Solve solveType) {
        this.graphic = true;
//        this.sudokuDisplay = new SudokuDisplay(this.starterSudoku);
        switch (solveType) {
            case basicSolve -> this.basicSolve();
            case threadSolve -> this.threadSolve();
            case fastestSolve -> this.fastestSolve();
        }
    }

//    Helper methods for solver
    private boolean isValidPlaceRow(int row, int number) {
        return Arrays.stream(this.helperSudoku[row]).noneMatch(x -> x == number);
    }

    private boolean isValidPlaceCol(int col, int number) {
        for (int[] row: this.helperSudoku) {
            if (row[col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidSquare(int row, int col, int number) {
        int squareX = col - col % 3;
        int squareY = row - row % 3;
        for (int y = squareY; y < squareY+3; y++) {
            for (int x = squareX; x < squareX+3; x++) {
                if (this.helperSudoku[y][x] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(int row, int col, int number) {
        return isValidPlaceRow(row, number) && isValidPlaceCol(col, number) && isValidSquare(row, col, number);
    }

    private int[][] copy(int[][] src) {
        if (src == null) {
            return null;
        }

        int[][] copy = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }

        return copy;
    }

}
