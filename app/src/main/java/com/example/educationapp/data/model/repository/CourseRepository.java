package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.CourseDao;
import com.example.educationapp.data.model.dao.UsersCoursesDao;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.UsersCourses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {
    private CourseDao courseDao;
    private UsersCoursesDao usersCoursesDao;
    private LiveData<List<Course>> courses;
    private ExecutorService executorService;

    // Конструктор репозитория
    public CourseRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        courseDao = database.courseDao();
        usersCoursesDao = database.usersCoursesDao();
        courses = courseDao.getAllCourses();
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
        return courses;
    }

    public LiveData<List<Course>> getAllCoursesForDirection(int directionID) {
        return courseDao.getAllCoursesForDirection(directionID);
    }

    LiveData<List<UsersCourses>> getAllCoursesForUser(int userID) {
        return usersCoursesDao.getAllCoursesForUser(userID);
    }

    public LiveData<List<Course>> getCoursesOfUser(int userID) {
        // Используем Transformations для преобразования одного LiveData в другое
        return Transformations.switchMap(usersCoursesDao.getAllCoursesForUser(userID), usersCoursesList -> {
            // Получаем список ID курсов
            List<Integer> courseIDs = new ArrayList<>();
            for (UsersCourses uc : usersCoursesList) {
                courseIDs.add(uc.getCourseID());
            }
            // Запрашиваем детали курсов по их ID
            return courseDao.getCoursesByIds(courseIDs);
        });
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}