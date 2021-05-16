package com.mmabas77.listtodoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Rebo {
   private TaskDao taskDao;
   LiveData<List<Task>>getAllList;

    public Rebo(Application application) {
       TaskDB db = TaskDB.getInstance(application);
       taskDao =db.taskDao();
       getAllList=taskDao.getAllList();
    }
    public void insertItem(Task task){
        new InsertAsyncTask(taskDao).execute(task);

    }
    public void updateItem(Task task){
        new UpdateAsyncTask(taskDao).execute(task);
    }
    public void deleteItem(Task task){
         new DeleteAsyncTask(taskDao).execute(task);
    }
    public LiveData<List<Task>>getGetAllList(){
        return taskDao.getAllList();
    }

    public static class InsertAsyncTask extends AsyncTask<Task,Void,Void> {

        private TaskDao taskDao;

        public InsertAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insertIten(tasks[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Task,Void,Void> {

        private TaskDao taskDao;

        public UpdateAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<Task,Void,Void> {

        private TaskDao taskDao;

        public DeleteAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.deleteItem(tasks[0]);
            return null;
        }
    }
}
