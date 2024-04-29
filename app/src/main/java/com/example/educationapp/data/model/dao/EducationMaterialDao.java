package com.example.educationapp.data.model.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.EducationMaterial;

import java.util.List;

@Dao
public interface EducationMaterialDao {
    @Query("SELECT * FROM education_material")
    LiveData<List<EducationMaterial>> getAllEducationMaterials();

    @Insert
    void insert(EducationMaterial educationMaterial);

    @Update
    void update(EducationMaterial educationMaterial);

    @Delete
    void delete(EducationMaterial educationMaterial);
}