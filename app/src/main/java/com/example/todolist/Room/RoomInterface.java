package com.example.todolist.Room;
import java.util.*;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RoomInterface {
    @Insert
    void insertTheEntity(ResponseModel responseModel);

    @Update
    void updateTheEntity(ResponseModel responseModel);

    @Delete
    void deleteTheEntity(ResponseModel responseModel);

    @Query("select * from TodoTable")
    List<ResponseModel>getAllData();
}
