package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.UserTaskResult;

import java.util.List;
@Dao
public interface UserTaskResultDao {
    @Insert
    void insert(UserTaskResult userTaskResult);

    @Update
    void update(UserTaskResult userTaskResult);

    @Delete
    void delete(UserTaskResult userTaskResult);
}