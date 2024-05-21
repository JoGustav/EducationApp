package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.entities.Course;

import java.util.List;

@Dao
public interface AchievementsDao {
    @Insert
    void insert(Achievements achievement);
    @Update
    void update(Achievements achievements);
    @Delete
    void delete(Achievements achievements);
    @Query("SELECT a.* FROM achievements a " +
            "INNER JOIN user_achievements ua ON a.achievementID = ua.achievementID " +
            "WHERE ua.userID = :userID")
    LiveData<List<Achievements>> getAchievementsForUser(int userID);
}
