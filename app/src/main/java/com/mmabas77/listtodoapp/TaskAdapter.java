package com.mmabas77.listtodoapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewData> {
    List<Task>taskList;
    Context context;
    Application application;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean checkStatus =false;
    Task task;

    public TaskAdapter(List<Task> taskList, Context context, Application application) {
        this.taskList = taskList;
        this.context = context;
        this.application = application;

    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design,parent,false);
        return new ViewData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, int position) {
        Task index = taskList.get(position);
       holder.textView.setText(index.getDesc());
        TaskViewModel taskViewModel = ViewModelProviders.of((FragmentActivity) context).get(TaskViewModel.class);
       holder.del.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               taskViewModel.deleteItem(index);
           }
       });


       if (index.isStatus()){
           holder.checkBox.setChecked(true);
       }
         else {
             holder.checkBox.setChecked(false);
         }

       holder.checkBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (holder.checkBox.isChecked()){
                   holder.checkBox.setChecked(true);
                   task = new Task(index.getDesc(),true);
                   task.setId(index.getId());
                   task.setStatus(true);
                   taskViewModel.updateData(task);

               }else {
                   task = new Task(index.getDesc(), false);
                   task.setId(index.getId());
                   task.setStatus(false);
                   taskViewModel.updateData(task);
                   holder.checkBox.setChecked(false);
               }
           }
       });


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddingTask.class);
                intent.putExtra(AddingTask.EXTRA_ID,index.getId());
                intent.putExtra(AddingTask.EXTRA_WORD,index.getDesc());
                intent.putExtra(AddingTask.EXTRA_MEANING,index.isStatus());



                context.startActivity(intent);
            }
        });









        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewData extends RecyclerView.ViewHolder{
        TextView textView;
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        ImageView del;

        public ViewData(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view_name);
            checkBox = itemView.findViewById(R.id.check_box_completed);
            relativeLayout = itemView.findViewById(R.id.click);
            del=itemView.findViewById(R.id.del);
        }
    }

}
