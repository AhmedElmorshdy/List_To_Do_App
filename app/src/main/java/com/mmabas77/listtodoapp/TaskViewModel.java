package com.mmabas77.listtodoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    Rebo rebo;
   public LiveData<List<Task>>getAllList;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        rebo = new Rebo(application);
        getAllList=rebo.getGetAllList();
    }
    public void insertData(Task task){
        rebo.insertItem(task);
    }
    public void deleteItem(Task task){
        rebo.deleteItem(task);
    }
    public void updateData(Task task){
        rebo.updateItem(task);
    }

}
