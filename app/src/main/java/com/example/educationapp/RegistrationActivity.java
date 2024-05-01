package com.example.educationapp;

import android.os.Bundle;

import com.example.educationapp.data.model.entities.User;
import com.example.educationapp.data.model.repository.UserRepository;
import com.example.educationapp.data.model.viewmodels.RegistrationViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.educationapp.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_registration);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_registration);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void addUserToDatabase() {
        RegistrationViewModel regViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

            User newUser = new User(
                    regViewModel.getUserName().getValue(),
                    regViewModel.getUserAge().getValue(),
                    regViewModel.getUserGender().getValue()
            );
            UserRepository userRepository = new UserRepository(getApplication());
            userRepository.insert(newUser);

        userRepository.getUserByNameGenderAge(newUser.getName(), newUser.getGender(), newUser.getAge()).observe(this, user -> {
            if (user != null) {
                // Пользователь найден, создаем сессию
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.createLoginSession(user.getUserID());
            } else {
                // Обработка ситуации, когда пользователь не найден
            }
        });
    }
}