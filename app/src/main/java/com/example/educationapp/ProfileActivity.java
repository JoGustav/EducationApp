package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
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
import com.example.educationapp.data.model.entities.User;
import com.example.educationapp.data.model.repository.UserAchievementsRepository;
import com.example.educationapp.data.model.repository.UserRepository;
import com.example.educationapp.data.model.viewmodels.AchievementViewModel;
import com.example.educationapp.data.model.viewmodels.AchievementViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView_ach;
    private UserRepository userRepository;
    private UserAchievementsRepository userAchievementsRepository;
    private AchievementViewModel achievementViewModel;
    private AchievementPowAdapter achievementPowAdapter;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ContributionView contributionView = findViewById(R.id.contributionView);
        contributionView.invalidate();

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            userID = sessionManager.getUserId();
        }

        userRepository = new UserRepository(this.getApplication());
        userRepository.getUserByUserID(userID).observe(this,user -> {

        });

        userAchievementsRepository = new UserAchievementsRepository(this.getApplication());
        AchievementViewModelFactory factory = new AchievementViewModelFactory(this.getApplication(), userID);
        achievementViewModel = new ViewModelProvider(this, factory).get(AchievementViewModel.class);

        achievementPowAdapter = new AchievementPowAdapter(userID, this, userAchievementsRepository);

        recyclerView_ach = findViewById(R.id.recyclerview_achievements);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ach.setLayoutManager(horizontalLayoutManager);
        recyclerView_ach.setAdapter(achievementPowAdapter);

        achievementViewModel.getAchievements().observe(this, new Observer<List<Achievements>>() {
            @Override
            public void onChanged(@Nullable List<Achievements> achievements) {
                achievementPowAdapter.setAchievements(achievements);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_all_directions) {
                // Переход к списку всех направлений
            } else if (id == R.id.navigation_my_courses) {

            } else if (id == R.id.navigation_profile) {

            }
            return true;
        });

    }

}
