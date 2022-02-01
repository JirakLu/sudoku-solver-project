package com.company;

import javax.swing.*;
import java.awt.*;

public class SudokuDisplay extends JFrame {

    private int[][] startedSudoku;

    private JLabel[][] labels;


    public SudokuDisplay(int[][] starterSudoku) {
        this.startedSudoku = starterSudoku;
        this.labels = new JLabel[starterSudoku.length][starterSudoku[0].length];

        this.setSize(700,700);
        this.setLocationRelativeTo(null);

        GridLayout gridMain = new GridLayout(9,9);
        gridMain.setVgap(1);
        gridMain.setHgap(1);

        JPanel panel = new JPanel();
        panel.setSize(603,603);
        panel.setLayout(gridMain);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel label = new JLabel("",SwingConstants.CENTER);
                label.setSize(67,67);
                label.setText(starterSudoku[row][col] == 0 ? "" : starterSudoku[row][col]+"");
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                label.setFont(new Font("Serif", Font.PLAIN, 35));
                if (starterSudoku[row][col] == 0) label.setForeground(Color.RED);
                labels[row][col] = label;
                panel.add(label);
            }
        }

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void updateCord(int x, int y, int number) {
        this.labels[x][y].setText(number == 0 ? "" : number+"");
    }

}
