package com.example.todolist.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TodoTable")
public class ResponseModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "comments")
    private String comments;

    @Ignore
    public ResponseModel(String title, String comments) {
        this.title = title;
        this.comments = comments;
    }

    @Ignore
    public ResponseModel(int id, String title, String comments) {
        this.id = id;
        this.title = title;
        this.comments = comments;
    }

    public ResponseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
