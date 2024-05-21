package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AchievementViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final int userID;

    public AchievementViewModelFactory(Application application, int userID) {
        this.application = application;
        this.userID = userID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AchievementViewModel.class)) {
            return (T) new AchievementViewModel(application, userID);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}


