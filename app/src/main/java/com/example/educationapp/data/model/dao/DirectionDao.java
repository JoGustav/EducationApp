package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.Direction;

import java.util.List;

@Dao
public interface DirectionDao {
    @Query("SELECT * FROM direction")
    LiveData<List<Direction>> getAllDirections();

    @Insert
    void insert(Direction direction);

    @Update
    void update(Direction direction);

    @Delete
    void delete(Direction direction);
}
