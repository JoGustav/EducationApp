package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.TaskDao;
import com.example.educationapp.data.model.entities.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService;

    // Конструктор репозитория
    public TaskRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }

    // Операция обновления проекта
    public void update(Task task) {
        executorService.execute(() -> taskDao.update(task));
    }

    // Операция удаления проекта
    public void delete(Task task) {
        executorService.execute(() -> taskDao.delete(task));
    }

    // Получение всех проектов
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}