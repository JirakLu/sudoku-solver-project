package com.company;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.IOException;

public class SudokuDisplay extends JPanel {

    private int[][] sudoku;

    private JTextField[][] labels;


    public SudokuDisplay() {
        this.labels = new JTextField[9][9];

        this.setLayout(new GridLayout(9,9));
        this.setPreferredSize(new Dimension(540,540));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(60,60));

                textField.setHorizontalAlignment(SwingConstants.CENTER);
                textField.setFont(new Font("SansSerif",Font.BOLD,30));
                textField.setForeground(Color.RED);

                this.addBorder(col, row, textField);

                labels[row][col] = textField;
                this.add(textField);
            }
        }
    }

    public void renderSudoku(int[][] sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
               this.labels[row][col].setText(sudoku[col][row] == 0 ? "" : sudoku[col][row] + "");
            }
        }
    }

    enum difficulty {easy, medium, hard}
    public int[][] generateSudoku(difficulty diff) throws IOException {

        // http request to API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sugoku.herokuapp.com/board?difficulty=" + diff)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        // parsing json to int[][]
        JSONObject jsonObj = new JSONObject(response.body().string());
        JSONArray jsonArr = jsonObj.getJSONArray("board");

        int[][] apiSudoku = new int[jsonArr.length()][9];

        for(int i = 0; i<jsonArr.length(); i++){
            JSONArray jsa1 = jsonArr.getJSONArray(i);
            for(int j = 0; j<jsa1.length();j++){
                apiSudoku[i][j] = jsa1.getInt(j);
            }
        }

        this.renderSudoku(apiSudoku);
        return apiSudoku;
    }

    private void addBorder(int col, int row, JTextField textField ) {
        col = col+1;
        row = row+1;

        if (col % 3 == 0 && row % 3 == 0 && col != 9 && row != 9) {
            textField.setBorder(new MatteBorder(1,1,3,3, Color.BLACK));
        } else if (row % 3 == 0 && row != 9) {
            textField.setBorder(new MatteBorder(1,1,3,1, Color.BLACK));
        } else if (col % 3 == 0 && col != 9) {
            textField.setBorder(new MatteBorder(1,1,1,3, Color.BLACK));
        } else {
            textField.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
        }
    }

    public void updateCord(int x, int y, int number) {
        this.labels[x][y].setText(number == 0 ? "" : number+"");
    }

}
