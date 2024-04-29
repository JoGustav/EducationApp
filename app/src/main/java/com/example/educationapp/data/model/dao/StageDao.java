package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.Stage;

import java.util.List;
@Dao
public interface StageDao {
    @Query("SELECT * FROM stage")
    LiveData<List<Stage>> getAllStages();

    @Insert
    void insert(Stage stage);

    @Update
    void update(Stage stage);

    @Delete
    void delete(Stage stage);
}
