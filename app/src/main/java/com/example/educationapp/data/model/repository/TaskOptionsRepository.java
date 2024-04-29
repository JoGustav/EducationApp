package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.TaskOptionsDao;
import com.example.educationapp.data.model.entities.TaskOptions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskOptionsRepository {
    private TaskOptionsDao taskOptionsDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public TaskOptionsRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        taskOptionsDao = database.taskOptionsDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(TaskOptions taskOptions) {
        executorService.execute(() -> taskOptionsDao.insert(taskOptions));
    }

    // Операция обновления проекта
    public void update(TaskOptions taskOptions) {
        executorService.execute(() -> taskOptionsDao.update(taskOptions));
    }

    // Операция удаления проекта
    public void delete(TaskOptions taskOptions) {
        executorService.execute(() -> taskOptionsDao.delete(taskOptions));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}