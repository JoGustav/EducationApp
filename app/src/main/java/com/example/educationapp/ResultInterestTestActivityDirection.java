package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ResultInterestTestActivityDirection extends AppCompatActivity {

    private Button buttonProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test_result_direction);

        buttonProceed = findViewById(R.id.buttonProceed); // замените на ID вашей кнопки
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultInterestTestActivityDirection.this, DirectionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
