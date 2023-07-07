package com.example.bingo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OfflineModeActivity extends AppCompatActivity {
    Button randomNumbers, customNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode);
        randomNumbers = findViewById(R.id.random_btn);
        customNumbers = findViewById(R.id.custom_btn);

        randomNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OfflineModeActivity.this,RandomBingoBoard.class));
            }
        });
        customNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OfflineModeActivity.this,CustomBingoBoard.class));
            }
        });
    }
}