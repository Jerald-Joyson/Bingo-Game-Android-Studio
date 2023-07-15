package com.example.bingo_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BingoBoard extends AppCompatActivity {
    private int[][] combinations = {
            {0, 1, 2, 3, 4},     // Horizontal combination
            {5, 6, 7, 8, 9},
            {10, 11, 12, 13, 14},
            {15, 16, 17, 18, 19},
            {20, 21, 22, 23, 24},
            {0, 5, 10, 15, 20},  // Vertical combination
            {1, 6, 11, 16, 21},
            {2, 7, 12, 17, 22},
            {3, 8, 13, 18, 23},
            {4, 9, 14, 19, 24},
            {0, 6, 12, 18, 24}, // Diagonal combination
            {4, 8, 12, 16, 20}
    };
    private static final int MATRIX_SIZE = 5;
    private List<String> clickedElements;
    private ArrayAdapter<String> adapter;
    LinearLayout buttonLayout;
    Button restartBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_board);
        buttonLayout = findViewById(R.id.buttonLayout);
        restartBtn = findViewById(R.id.restart_btn);
        exitBtn = findViewById(R.id.exit_btn);
        GridView gridView = findViewById(R.id.gridView);
        clickedElements = new ArrayList<>();
        List<String> matrixValues = getIntent().getStringArrayListExtra("matrixValues");
        adapter = new ArrayAdapter<>(
                this,
                R.layout.grid_item_layout,matrixValues

        );

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapter.getItem(position);
                if (!clickedElements.contains(item)) {
                    clickedElements.add(item);

                    view.setBackgroundColor(Color.BLACK);
                    checkCombinations();
                }
                takeElementAtIndex(item);
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BingoBoard.this, ModeScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLetter(0);
                clearBlueColor();
            }
        });
    }
    private void takeElementAtIndex(String item) {
        Toast.makeText(getApplicationContext(), "Clicked element: " + item, Toast.LENGTH_SHORT).show();
    }

    private void checkCombinations() {
        int count = 0;
        for (int[] combination : combinations) {
            boolean marked = true;
            for (int index : combination) {
                String number = adapter.getItem(index);
                if (!clickedElements.contains(number)) {
                    marked = false;
                    break;
                }
            }
            if (marked) {
                count++;
                displayLetter(count);
                if (count == 5) {
                    showButtons();
                    displayCombinationsCount(count);
                }
            }
        }

    }

    private void displayLetter(int count) {
        TextView bTextView = findViewById(R.id.b_text);
        TextView iTextView = findViewById(R.id.i_text);
        TextView nTextView = findViewById(R.id.n_text);
        TextView gTextView = findViewById(R.id.g_text);
        TextView oTextView = findViewById(R.id.o_text);
        if (count == 0) {
            bTextView.setVisibility(View.GONE);
            iTextView.setVisibility(View.GONE);
            nTextView.setVisibility(View.GONE);
            gTextView.setVisibility(View.GONE);
            oTextView.setVisibility(View.GONE);
        }
        if (count == 1) {
            bTextView.setVisibility(View.VISIBLE);
        }
        if (count == 2) {
            iTextView.setVisibility(View.VISIBLE);
        }
        if (count == 3) {
            nTextView.setVisibility(View.VISIBLE);
        }
        if (count == 4) {
            gTextView.setVisibility(View.VISIBLE);
        }
        if (count == 5) {
            oTextView.setVisibility(View.VISIBLE);
        }

    }

    private void showButtons() {
        buttonLayout.setVisibility(View.VISIBLE);
    }

    private void displayCombinationsCount(int count) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("!!..B_I_N_G_O..!!");
        builder.setMessage("Congratulations It Is BINGO..!!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void clearBlueColor() {
        GridView gridView = findViewById(R.id.gridView);
        int childCount = gridView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = gridView.getChildAt(i);
            childView.setBackgroundColor(Color.WHITE);
        }
        clickedElements.clear();
    }
}



