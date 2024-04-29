package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.TaskOptions;

import java.util.List;

@Dao
public interface TaskOptionsDao {
    @Insert
    void insert(TaskOptions taskOptions);

    @Update
    void update(TaskOptions taskOptions);

    @Delete
    void delete(TaskOptions taskOptions);
}