package com.example.educationapp.data.model.entities;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "education_material",
        foreignKeys = {
                @ForeignKey(entity = Stage.class,
                        parentColumns = "stageID",
                        childColumns = "stageID",
                        onDelete = ForeignKey.CASCADE),
        })
public class EducationMaterial extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int educationMaterialID;
    @ColumnInfo(name = "StageID", index = true)
    private int stageID;
    @ColumnInfo(name = "Content")
    private String content;
    @ColumnInfo(name = "Type")
    private String type;

    public EducationMaterial(int educationMaterialID, int stageID, String content, String type) {
        this.educationMaterialID = educationMaterialID;
        this.stageID = stageID;
        this.content = content;
        this.type = type;
    }

    @Bindable
    public int getEducationMaterialID() { return educationMaterialID; }
    public void setEducationMaterialID(int educationMaterialID) {
        this.educationMaterialID = educationMaterialID;
        notifyPropertyChanged(BR.educationMaterialID);
    }

    @Bindable
    public int getStageID() { return stageID; }
    public void setStageID(int stageID) {
        this.stageID = stageID;
        notifyPropertyChanged(BR.stageID);
    }

    @Bindable
    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
}