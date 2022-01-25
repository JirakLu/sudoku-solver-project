package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SudokuDisplay extends JFrame {

    private int[][] startedSudoku;


    public SudokuDisplay(int[][] starterSudoku) {
        this.startedSudoku = starterSudoku;

        this.setSize(700,700);
        this.setLocationRelativeTo(null);

        GridLayout gridMain = new GridLayout(3,3);
        gridMain.setHgap(10);
        gridMain.setVgap(10);

        JPanel panel = new JPanel();
        panel.setSize(600,600);
        panel.setLayout(gridMain);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        for (int i = 0; i < 9; i++) {
            JPanel panelicek = new JPanel();

            GridLayout grid = new GridLayout(3,3);
            grid.setHgap(10);
            grid.setVgap(10);

            panelicek.setSize(190,190);
            panelicek.setLayout(grid);
            panelicek.setBorder(BorderFactory.createLineBorder(Color.black));

            for (int j = 0; j < 9; j++) {
                JLabel label = new JLabel("",SwingConstants.CENTER);
                label.setSize(60,60);
                label.setText(j+1+"");
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                label.setFont(new Font("Serif", Font.PLAIN, 25));
                panelicek.add(label);
            }
            panel.add(panelicek);
        }

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void rerenderSudoku(int[][] halfSolved) {

    }

}
