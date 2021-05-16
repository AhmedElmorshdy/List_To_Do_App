package com.mmabas77.listtodoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddViewModel extends AndroidViewModel {
    public Rebo rebo;
    public AddViewModel(@NonNull Application application) {
        super(application);
        rebo = new Rebo(application);

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
