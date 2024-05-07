package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.educationapp.DirectionsActivity;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.entities.User;
import com.example.educationapp.data.model.entities.UsersCourses;
import com.example.educationapp.data.model.repository.CourseRepository;
import com.example.educationapp.data.model.repository.DirectionRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private final CourseRepository courseRepository;
    private final LiveData<List<Course>> allCourses;
    private LiveData<List<Course>> allCoursesForDirection;
    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
        allCourses = courseRepository.getAllCourses();
    }
    public void insert(Course course){
        courseRepository.insert(course);
    }
    public void update(Course course){
        courseRepository.update(course);
    }
    public void delete(Course course){
        courseRepository.delete(course);
    }
    public LiveData<List<Course>> getAllCourses() {return allCourses;}
    public LiveData<List<Course>> getAllCoursesForDirection(int directionID)
    {
        allCoursesForDirection = courseRepository.getAllCoursesForDirection(directionID);
        return allCoursesForDirection;
    }

    public LiveData<List<Course>> getAllCoursesOfUser(int userId) {
        return courseRepository.getCoursesOfUser(userId);
    }
}