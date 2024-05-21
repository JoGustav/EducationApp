package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.repository.UserAchievementsRepository;

import java.util.List;

public class AchievementViewModel extends AndroidViewModel {
    private final LiveData<List<Achievements>> achievements;
    private final UserAchievementsRepository repository;

    public AchievementViewModel(@NonNull Application application, int userID) {
        super(application);
        repository = new UserAchievementsRepository(application);
        achievements = repository.getAchievementsForUser(userID);
    }

    public LiveData<List<Achievements>> getAchievements() {
        return achievements;
    }
}


