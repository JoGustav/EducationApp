package com.example.educationapp.data.model.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrationViewModel extends ViewModel {
    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<Integer> userAge = new MutableLiveData<>();
    private final MutableLiveData<String> userGender = new MutableLiveData<>();

    // Методы для установки значений
    public void setUserName(String name) {
        userName.setValue(name);
    }

    public void setUserAge(Integer age) {
        userAge.setValue(age);
    }

    public void setUserGender(String gender) {
        userGender.setValue(gender);
    }

    // Методы для получения значений
    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<Integer> getUserAge() {
        return userAge;
    }

    public MutableLiveData<String> getUserGender() {
        return userGender;
    }
}
