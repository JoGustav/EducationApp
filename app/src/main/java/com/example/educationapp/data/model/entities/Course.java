package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;
@Entity(tableName = "course",
foreignKeys = {
        @ForeignKey(entity = Direction.class,
        parentColumns = "directionID",
        childColumns = "directionID",
        onDelete = ForeignKey.CASCADE),
})
public class Course extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int courseID;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "DirectionID", index = true)
    private int directionID;

    public Course(int courseID, String title, String description, int directionID) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.directionID = directionID;
    }

    @Bindable
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
        notifyPropertyChanged(BR.courseID);
    }

    @Bindable
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public int getDirectionID() { return directionID; }
    public void setDirectionID(int directionID) {
        this.directionID = directionID;
        notifyPropertyChanged(BR.directionID);
    }
}
