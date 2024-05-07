package com.example.educationapp.data.model.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Stage;
import com.example.educationapp.data.model.repository.CourseRepository;
import com.example.educationapp.data.model.repository.StageRepository;

import java.util.List;

public class StageViewModel extends AndroidViewModel {
    private final StageRepository stageRepository;
    private LiveData<List<Stage>> allStagesForCourse;
    public StageViewModel(@NonNull Application application) {
        super(application);
        stageRepository = new StageRepository(application);
    }
    public void insert(Stage stage){
        stageRepository.insert(stage);
    }
    public void update(Stage stage){
        stageRepository.update(stage);
    }
    public void delete(Stage stage){
        stageRepository.delete(stage);
    }

    public LiveData<Integer> getCountStagesForCourse(int courseID) {
        return stageRepository.getCountStagesForCourse(courseID);
    }
    public LiveData<List<Stage>> getAllStagesForCourse(int courseID)
    {
        allStagesForCourse = stageRepository.getAllStagesForCourse(courseID);
        return allStagesForCourse;
    }
}