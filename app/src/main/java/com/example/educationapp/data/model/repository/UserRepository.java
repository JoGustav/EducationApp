package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.UserDao;
import com.example.educationapp.data.model.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private ExecutorService executorService;

    // Конструктор репозитория
    public UserRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    // Операция обновления проекта
    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

    // Операция удаления проекта
    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }

    // Получение всех проектов
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}