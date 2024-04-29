package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.UserDao;
import com.example.educationapp.data.model.dao.UserTaskResultDao;
import com.example.educationapp.data.model.entities.User;
import com.example.educationapp.data.model.entities.UserTaskResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserTaskResultRepository {
    private UserTaskResultDao userTaskResultDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public UserTaskResultRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        userTaskResultDao = database.userTaskResultDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(UserTaskResult userTaskResult) {
        executorService.execute(() -> userTaskResultDao.insert(userTaskResult));
    }

    // Операция обновления проекта
    public void update(UserTaskResult userTaskResult) {
        executorService.execute(() -> userTaskResultDao.update(userTaskResult));
    }

    // Операция удаления проекта
    public void delete(UserTaskResult userTaskResult) {
        executorService.execute(() -> userTaskResultDao.delete(userTaskResult));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}