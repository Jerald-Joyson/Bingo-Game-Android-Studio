package com.example.bingo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeScreenActivity extends AppCompatActivity {
    Button offlineMode, onlineMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_screen);
        offlineMode = findViewById(R.id.offline_mode_btn);
        onlineMode = findViewById(R.id.online_mode_btn);

        offlineMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModeScreenActivity.this,OfflineModeActivity.class));
            }
        });
        onlineMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModeScreenActivity.this,MainActivity.class));
            }
        });
    }
}