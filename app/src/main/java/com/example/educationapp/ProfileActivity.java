package com.example.educationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.AchievementPowAdapter;
import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.repository.UserAchievementsRepository;
import com.example.educationapp.data.model.viewmodels.AchievementViewModel;
import com.example.educationapp.data.model.viewmodels.AchievementViewModelFactory;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressPercentage;
    private RecyclerView recyclerView_ach;
    private UserAchievementsRepository userAchievementsRepository;
    private AchievementViewModel achievementViewModel;
    private AchievementPowAdapter achievementPowAdapter;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            userID = sessionManager.getUserId();
        }
        userAchievementsRepository = new UserAchievementsRepository(this.getApplication());
        AchievementViewModelFactory factory = new AchievementViewModelFactory(this.getApplication(), userID);
        achievementViewModel = new ViewModelProvider(this, factory).get(AchievementViewModel.class);

        achievementPowAdapter = new AchievementPowAdapter(userID, this, userAchievementsRepository);

        recyclerView_ach = findViewById(R.id.recyclerview_achievements);
        recyclerView_ach.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_ach.setAdapter(achievementPowAdapter);

        // Observe the LiveData from the ViewModel
        achievementViewModel.getAchievements().observe(this, new Observer<List<Achievements>>() {
            @Override
            public void onChanged(@Nullable List<Achievements> achievements) {
                achievementPowAdapter.setAchievements(achievements);
            }
        });

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
