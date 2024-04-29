package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.CourseProgressDao;
import com.example.educationapp.data.model.entities.CourseProgress;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseProgressRepository {
    private CourseProgressDao courseProgressDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public CourseProgressRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        courseProgressDao = database.courseProgressDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(CourseProgress courseProgress) {
        executorService.execute(() -> courseProgressDao.insert(courseProgress));
    }

    // Операция обновления проекта
    public void update(CourseProgress courseProgress) {
        executorService.execute(() -> courseProgressDao.update(courseProgress));
    }

    // Операция удаления проекта
    public void delete(CourseProgress courseProgress) {
        executorService.execute(() -> courseProgressDao.delete(courseProgress));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}