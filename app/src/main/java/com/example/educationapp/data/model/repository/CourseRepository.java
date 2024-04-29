package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.CourseDao;
import com.example.educationapp.data.model.entities.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {
    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;
    private ExecutorService executorService;

    // Конструктор репозитория
    public CourseRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        courseDao = database.courseDao();
        allCourses = courseDao.getAllCourses();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(Course course) {
        executorService.execute(() -> courseDao.insert(course));
    }

    // Операция обновления проекта
    public void update(Course course) {
        executorService.execute(() -> courseDao.update(course));
    }

    // Операция удаления проекта
    public void delete(Course course) {
        executorService.execute(() -> courseDao.delete(course));
    }

    // Получение всех проектов
    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }


    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}