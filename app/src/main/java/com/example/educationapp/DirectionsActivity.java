package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.educationapp.databinding.ActivityDirectionsBinding;

public class DirectionsActivity extends AppCompatActivity {

    private ActivityDirectionsBinding binding;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name = binding.Name;

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            int userId = sessionManager.getUserId();
            name.setText(String.valueOf(userId));
        }
    }
}