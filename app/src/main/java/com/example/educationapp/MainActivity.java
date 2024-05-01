package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Проверяем, авторизован ли пользователь
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            // Пользователь уже авторизован, переходим в нужное Activity
            Intent intent = new Intent(MainActivity.this, DirectionsActivity.class);
            startActivity(intent);
            finish(); // Завершаем MainActivity, чтобы пользователь не мог вернуться к экрану регистрации
        } else {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish(); // Завершаем MainActivity, чтобы пользователь не мог вернуться к экрану регистрации
        }
    }
}