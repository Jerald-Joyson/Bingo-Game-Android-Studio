package com.example.bingo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomBingoBoard extends AppCompatActivity {
    private static final int MATRIX_SIZE = 5;
    private ArrayAdapter<String> adapter;
    List<String> matrixValues = generateNumbersList();
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_bingo_board);
        saveBtn = findViewById(R.id.save_btn);
        GridView gridView = findViewById(R.id.gridView);

        adapter = new ArrayAdapter<>(
                this,
                R.layout.grid_item_layout,
                matrixValues
        );
        gridView.setAdapter(adapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomBingoBoard.this, BingoBoard.class);
                intent.putStringArrayListExtra("matrixValues", (ArrayList<String>) matrixValues);
                startActivity(intent);
            }
        });

    }
    private List<String> generateNumbersList() {
        List<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            numbersList.add(String.valueOf(i));
        }

        Collections.shuffle(numbersList); // Shuffle the list to randomize the order

        return numbersList;
    }
    public void restartScreen(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}