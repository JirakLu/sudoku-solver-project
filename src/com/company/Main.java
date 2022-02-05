package com.company;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sugoku.herokuapp.com/board?difficulty=hard")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonObj = new JSONObject(response.body().string());
        JSONArray jsonArr = jsonObj.getJSONArray("board");

        int[][] apiSudoku = new int[jsonArr.length()][9];

        for(int i = 0; i<jsonArr.length(); i++){
            JSONArray jsa1 = jsonArr.getJSONArray(i);

            for(int j = 0; j<jsa1.length();j++){
                apiSudoku[i][j] = jsa1.getInt(j);
            }

        }


        Sudoku sudoku = new Sudoku(apiSudoku);
        sudoku.graphicSolve(Sudoku.Solve.basicSolve);
        sudoku.timeSolve(Sudoku.Solve.basicSolve);

    }
}
