package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.educationapp.DirectionsActivity;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.repository.DirectionRepository;

import java.util.List;

public class DirectionViewModel extends AndroidViewModel {
    private final DirectionRepository directionRepository;
    private final LiveData<List<Direction>> allDirections;
    public DirectionViewModel(@NonNull Application application) {
        super(application);
        directionRepository = new DirectionRepository(application);
        allDirections = directionRepository.getAllDirections();
    }
    public void insert(Direction direction){
        directionRepository.insert(direction);
    }
    public void update(Direction direction){
        directionRepository.update(direction);
    }
    public void delete(Direction direction){
        directionRepository.delete(direction);
    }
    public LiveData<List<Direction>> getAllDirections(){
        return allDirections;
    }
}