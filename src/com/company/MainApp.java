package com.company;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainApp extends JFrame {

    private JPanel wrapper;

    private SudokuDisplay sudokuDisplay;
    private JPanel sudokuGenerator;
    private JPanel sudokuSolverInfo;

    public MainApp() {
        this.wrapper = new JPanel();
        wrapper.setSize(800,900);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        this.createSudokuGenerator();
        this.sudokuDisplay = new SudokuDisplay();
        sudokuDisplay.setMaximumSize(new Dimension(540,540));
        this.createSudokuSolverInfo();

        wrapper.add(sudokuGenerator);
        wrapper.add(sudokuDisplay);
        wrapper.add(sudokuSolverInfo);

        this.setSize(800,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setContentPane(wrapper);
        this.pack();
        this.setVisible(true);
    }

    private void createSudokuGenerator() {
        this.sudokuGenerator = new JPanel();
        sudokuGenerator.setPreferredSize(new Dimension(800,100));

        JRadioButton easy = new JRadioButton("easy");
        easy.setActionCommand("easy");
        JRadioButton medium = new JRadioButton("medium");
        medium.setActionCommand("medium");
        JRadioButton hard = new JRadioButton("hard", true);
        hard.setActionCommand("hard");
        Button generate = new Button("Generate!");

        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(easy);
        btnGrp.add(medium);
        btnGrp.add(hard);

        sudokuGenerator.add(easy);
        sudokuGenerator.add(medium);
        sudokuGenerator.add(hard);
        sudokuGenerator.add(generate);

        generate.addActionListener(e -> {
            try {
                sudokuDisplay.generateSudoku(SudokuDisplay.difficulty.valueOf(btnGrp.getSelection().getActionCommand()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void createSudokuSolverInfo() {
        this.sudokuSolverInfo = new JPanel();
        sudokuGenerator.setPreferredSize(new Dimension(800,300));
    }

}
