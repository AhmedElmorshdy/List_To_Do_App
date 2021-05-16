package com.mmabas77.listtodoapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertIten(Task task);
    @Delete
    void deleteItem(Task task);
    @Query("SELECT * FROM TASK_TABLE")
    LiveData<List<Task>>getAllList();

    @Update
    void update(Task task);
}
