package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.entities.UserAchievements;

@Dao
public interface UserAchievementsDao {
    @Insert
    void insert(UserAchievements achievement);
    @Update
    void update(UserAchievements achievements);
    @Delete
    void delete(UserAchievements achievements);
}
