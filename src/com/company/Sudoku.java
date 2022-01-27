package com.company;

import java.util.Arrays;

public class Sudoku {

    private int[][] starterSudoku;
    private int[][] helperSudoku;
    public int[][] solvedSudoku;

    private int sudokuHeight;
    private int sudokuWidth;

    private SudokuDisplay sudokuDisplay;

    public Sudoku(int[][] sudoku) {
        this.starterSudoku = sudoku;
        this.helperSudoku = sudoku;
        this.sudokuHeight = sudoku.length;
        this.sudokuWidth = sudoku[0].length;
        this.sudokuDisplay = new SudokuDisplay(sudoku);
    }

    public boolean basicSolve() throws InterruptedException {
        for (int row = 0; row < this.sudokuHeight; row++) {
            for (int col = 0; col < this.sudokuWidth; col++) {
                if (this.helperSudoku[row][col] == 0) {
                    for (int number = 0; number <= 9; number++) {
                        if (isValidPlacement(row, col, number)) {
                            this.helperSudoku[row][col] = number;
                            this.sudokuDisplay.updateCoord(row,col,number);
                            if (basicSolve()) {
                                return true;
                            } else {
                                this.helperSudoku[row][col] = 0;
                                this.sudokuDisplay.updateCoord(row,col,0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        for (int[] row: this.helperSudoku) {
            System.out.println(Arrays.toString(row));
        }
        this.solvedSudoku = this.helperSudoku;
        return true;
    }

    private boolean isValidPlaceRow(int row, int number) {
        return !Arrays.stream(this.starterSudoku[row]).anyMatch(x -> x == number);
    }

    private boolean isValidPlaceCol(int col, int number) {
        for (int[] row: this.starterSudoku) {
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
                if (this.starterSudoku[y][x] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(int row, int col, int number) {
        return isValidPlaceRow(row, number) && isValidPlaceCol(col, number) && isValidSquare(row, col, number);
    }

}
