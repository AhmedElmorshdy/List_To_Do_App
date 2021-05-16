package com.mmabas77.listtodoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;

public class Utils {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Utils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("file",Context.MODE_PRIVATE);
         editor=sharedPreferences.edit();
    }
    public void setStatus(boolean b){
        editor.putBoolean("KAY",b);
        editor.commit();
    }
    public boolean stat(boolean b){
        boolean h = sharedPreferences.getBoolean("KAY",false);
        return h;
    }
}
