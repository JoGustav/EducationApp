package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.UsersCourses;
import com.example.educationapp.data.model.repository.UsersCoursesRepository;

public class UsersCoursesViewModel extends AndroidViewModel {
    private final UsersCoursesRepository usersCoursesRepository;

    public UsersCoursesViewModel(@NonNull Application application) {
        super(application);
        usersCoursesRepository = new UsersCoursesRepository(application);

    }
    public void insert(UsersCourses usersCourses){
        usersCoursesRepository.insert(usersCourses);
    }
    public void update(UsersCourses usersCourses){
        usersCoursesRepository.update(usersCourses);
    }
    public void delete(UsersCourses usersCourses){
        usersCoursesRepository.delete(usersCourses);
    }
}
