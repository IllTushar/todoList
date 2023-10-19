package com.example.todolist.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = ResponseModel.class, exportSchema = false, version = 1)
public abstract class RequestModel extends RoomDatabase {
    private static final String DB_NAME = "ToDoList";
    private static RequestModel requestModel;

    public static synchronized RequestModel getRequestModel(Context context) {
        if (requestModel == null) {
          requestModel = Room.databaseBuilder(context,RequestModel.class,DB_NAME)
                  .fallbackToDestructiveMigration()
                  .allowMainThreadQueries()
                  .build();
        }
        return requestModel;
    }

    public abstract RoomInterface roomInterface();
}
