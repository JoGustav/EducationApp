package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "task",
        foreignKeys = {
                @ForeignKey(entity = Stage.class,
                        parentColumns = "stageID",
                        childColumns = "stageID",
                        onDelete = ForeignKey.CASCADE),
        })
public class Task extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int taskID;
    @ColumnInfo(name = "StageID", index = true)
    private int stageID;
    @ColumnInfo(name = "Question")
    private String question;
    @ColumnInfo(name = "Type")
    private String type;
    @ColumnInfo(name = "CorrectAnswer")
    private String correctAnswer;

    public Task(int taskID, int stageID, String question, String type, String correctAnswer) {
        this.taskID = taskID;
        this.stageID = stageID;
        this.question = question;
        this.type = type;
        this.correctAnswer = correctAnswer;
    }

    @Bindable
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
        notifyPropertyChanged(BR.taskID);
    }

    @Bindable
    public int getStageID() { return stageID; }
    public void setStageID(int stageID) {
        this.stageID = stageID;
        notifyPropertyChanged(BR.stageID);
    }

    @Bindable
    public String getQuestion() { return question; }
    public void setQuestion(String question) {
        this.question = question;
        notifyPropertyChanged(BR.question);
    }

    @Bindable
    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        notifyPropertyChanged(BR.correctAnswer);
    }
}