package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.UsersCoursesDao;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.UsersCourses;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsersCoursesRepository {
    private UsersCoursesDao usersCoursesDao;
    private ExecutorService executorService;

    public UsersCoursesRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        usersCoursesDao = database.usersCoursesDao();
        executorService = Executors.newFixedThreadPool(3);
    }

    // Операция вставки проекта
    public void insert(UsersCourses usersCourses) {
        executorService.execute(() -> usersCoursesDao.insert(usersCourses));
    }

    // Операция обновления проекта
    public void update(UsersCourses usersCourses) {
        executorService.execute(() -> usersCoursesDao.update(usersCourses));
    }

    // Операция удаления проекта
    public void delete(UsersCourses usersCourses) {
        executorService.execute(() -> usersCoursesDao.delete(usersCourses));
    }
    public void close() {
        executorService.shutdown();
    }
}
