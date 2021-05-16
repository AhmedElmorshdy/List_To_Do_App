package com.mmabas77.listtodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  TaskViewModel taskViewModel;
  RecyclerView recyclerView;
  RecyclerView.LayoutManager layoutManager;
  ArrayList<Task>taskArrayList;
  TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllList.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                 taskAdapter = new TaskAdapter(tasks,MainActivity.this,(Application)getApplicationContext());
                 recyclerView.setAdapter(taskAdapter);
                 taskAdapter.notifyDataSetChanged();
            }
        });







    }


    public void add(View view) {
        Intent intent = new Intent(MainActivity.this,AddingTask.class);
        startActivity(intent);
    }
}