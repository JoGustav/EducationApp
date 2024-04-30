package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "stage",
        foreignKeys = {
                @ForeignKey(entity = Course.class,
                        parentColumns = "courseID",
                        childColumns = "courseID",
                        onDelete = ForeignKey.CASCADE),
        })
public class Stage extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int stageID;
    @ColumnInfo(index = true)
    @NonNull
    private int courseID;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "Order")
    private int order;

    public Stage(int courseID, String title, String description, int order) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.order = order;
    }

    @Bindable
    public int getStageID() { return stageID; }
    public void setStageID(int stageID) {
        this.stageID = stageID;
        notifyPropertyChanged(BR.stageID);
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
    public int getOrder() { return order; }
    public void setOrder(int order) {
        this.order = order;
        notifyPropertyChanged(BR.order);
    }
}