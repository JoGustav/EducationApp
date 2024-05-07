package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.CourseProgress;
import com.example.educationapp.data.model.repository.CourseProgressRepository;
import com.example.educationapp.data.model.repository.CourseRepository;

import java.util.List;

public class CourseProgressViewModel extends AndroidViewModel {
    private final CourseProgressRepository courseProgressRepository;
    public CourseProgressViewModel(@NonNull Application application) {
        super(application);
        courseProgressRepository = new CourseProgressRepository(application);
    }
    public void insert(CourseProgress courseProgress){
        courseProgressRepository.insert(courseProgress);
    }
    public void update(CourseProgress courseProgress){
        courseProgressRepository.update(courseProgress);
    }
    public void delete(CourseProgress courseProgress){
        courseProgressRepository.delete(courseProgress);
    }
    public LiveData<List<CourseProgress>> getAllCourseProgressForUser(int courseID, int userID) {
        return courseProgressRepository.getCourseProgressForUser(courseID,userID);
    }
    public LiveData<Integer> getCompletedStagesCountForCourse(int courseID, int userID) {
        return courseProgressRepository.getCompletedStagesCountForCourse(courseID,userID);
    }
    public LiveData<Integer> getTotalStagesCountForCourse(int courseID, int userID) {
        return courseProgressRepository.getTotalStagesCountForCourse(courseID,userID);
    }
}