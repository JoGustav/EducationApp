package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "interest_test_answers",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "userID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = InterestTestQuestions.class,
                        parentColumns = "questionID",
                        childColumns = "questionID",
                        onDelete = ForeignKey.CASCADE),
        })
public class InterestTestAnswers extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int answerID;
    @ColumnInfo(index = true)
    @NonNull
    private int userID;
    @ColumnInfo(index = true)
    @NonNull
    private int questionID;
    @ColumnInfo(name = "Answer")
    private int answer;

    public InterestTestAnswers(int userID, int questionID, int answer) {
        this.userID = userID;
        this.questionID = questionID;
        this.answer = answer;
    }

    @Bindable
    public int getAnswerID() { return answerID; }
    public void setAnswerID(int answerID) {
        this.answerID = answerID;
        notifyPropertyChanged(BR.answerID);
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public int getQuestionID() { return questionID; }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
        notifyPropertyChanged(BR.questionID);
    }

    @Bindable
    public int getAnswer() { return answer; }
    public void setAnswer(int answer) {
        this.answer = answer;
        notifyPropertyChanged(BR.answer);
    }
}