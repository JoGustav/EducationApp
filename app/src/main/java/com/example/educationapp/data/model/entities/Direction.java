package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "direction")
public class Direction extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int directionID;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Description")
    private String description;

    public Direction(int directionID, String title, String description) {
        this.directionID = directionID;
        this.title = title;
        this.description = description;
    }

    @Bindable
    public int getDirectionID() { return directionID; }
    public void setDirectionID(int directionID) {
        this.directionID = directionID;
        notifyPropertyChanged(BR.directionID);
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
}