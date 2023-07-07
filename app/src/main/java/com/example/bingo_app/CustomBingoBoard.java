package com.example.bingo_app;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomBingoBoard extends AppCompatActivity {
    private EditText[] editTexts;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bingo_board);
        editTexts = new EditText[25];
        editTexts[0] = findViewById(R.id.editText1);
        editTexts[1] = findViewById(R.id.editText2);
        editTexts[2] = findViewById(R.id.editText3);
        editTexts[3] = findViewById(R.id.editText4);
        editTexts[4] = findViewById(R.id.editText5);
        editTexts[5] = findViewById(R.id.editText6);
        editTexts[6] = findViewById(R.id.editText7);
        editTexts[7] = findViewById(R.id.editText8);
        editTexts[8] = findViewById(R.id.editText9);
        editTexts[9] = findViewById(R.id.editText10);
        editTexts[10] = findViewById(R.id.editText11);
        editTexts[11] = findViewById(R.id.editText12);
        editTexts[12] = findViewById(R.id.editText13);
        editTexts[13] = findViewById(R.id.editText14);
        editTexts[14] = findViewById(R.id.editText15);
        editTexts[15] = findViewById(R.id.editText16);
        editTexts[16] = findViewById(R.id.editText17);
        editTexts[17] = findViewById(R.id.editText18);
        editTexts[18] = findViewById(R.id.editText19);
        editTexts[19] = findViewById(R.id.editText20);
        editTexts[20] = findViewById(R.id.editText21);
        editTexts[21] = findViewById(R.id.editText22);
        editTexts[22] = findViewById(R.id.editText23);
        editTexts[23] = findViewById(R.id.editText24);
        editTexts[24] = findViewById(R.id.editText25);

        // Assign other EditTexts to respective indices of the array

        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateMatrix()) {
                    List<String> matrixValues = getMatrixValues();

                    Intent intent = new Intent(CustomBingoBoard.this, BingoBoard.class);
                    intent.putStringArrayListExtra("matrixValues", (ArrayList<String>) matrixValues);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateMatrix() {
        Set<String> numbersSet = new HashSet<>();
        boolean isValid = true;

        for (EditText editText : editTexts) {
            String number = editText.getText().toString().trim();

            if (number.isEmpty()) {
                editText.setError("Please enter a number");
                isValid = false;
            } else {
                int num = Integer.parseInt(number);
                if (num < 1 || num > 25) {
                    showAlertDialog("Invalid Number", "Number should be between 1 and 25");
                    isValid = false;
                } else if (numbersSet.contains(number)) {
                    showAlertDialog("Invalid Number", "Number already inserted");
                    isValid = false;
                } else {
                    numbersSet.add(number);
                }
            }
        }

        return isValid;
    }

    private List<String> getMatrixValues() {
        List<String> matrixValues = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                EditText editText = editTexts[i * 5 + j];
                String number = editText.getText().toString().trim();
                matrixValues.add(number);
            }
        }

        return matrixValues;
    }

    private void showAlertDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CustomBingoBoard.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}