package com.example.educationapp;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar = findViewById(R.id.circular_progress_bar);
        progressPercentage = findViewById(R.id.progress_percentage);

        // Установка значения прогресса
        setProgressValue(75); // Пример установки значения в 75%
    }

    private void setProgressValue(int progress) {
        progressBar.setProgress(progress);
        progressPercentage.setText(progress + "%");
    }
}
