package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InterestTestViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    public InterestTestViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InterestTestViewModel.class)) {
            return (T) new InterestTestViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
