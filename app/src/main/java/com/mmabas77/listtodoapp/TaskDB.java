package com.mmabas77.listtodoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Task.class,version = 2)
public abstract class TaskDB extends RoomDatabase {
    public static TaskDB instance;
    public abstract TaskDao taskDao();

    public static synchronized TaskDB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,TaskDB.class,"task_database").fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
