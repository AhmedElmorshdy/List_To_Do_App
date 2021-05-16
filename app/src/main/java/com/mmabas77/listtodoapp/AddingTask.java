package com.mmabas77.listtodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddingTask extends AppCompatActivity {
    EditText editText;
    AddViewModel addViewModel;
    boolean status1 = false;
    String update;
    int id;
    Task task;
    boolean status;
    TimePickerDialog timePickerDialog;
    Button setalarm;
    Button shaw;
    TextView hour,min;
    Intent intent;
    String c;
    Calendar calendar;
    int currentHour,currentMin;
    PendingIntent pendingIntent;
    String h,m;

    public static final String EXTRA_ID = "com.example.wordlist.extraid";
    public static final String EXTRA_WORD = "com.example.wordlist.word";
    public static final String EXTRA_MEANING = "com.example.wordlist.meaning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);
        editText = findViewById(R.id.editTextTextPersonName);


       // setalarm = findViewById(R.id.button);




        Intent intent = getIntent();



        if (intent.hasExtra(EXTRA_ID)){
             status1 = true;
            id = intent.getIntExtra(EXTRA_ID,-1);
            status =intent.getBooleanExtra(EXTRA_MEANING,true);
            editText.setText(intent.getStringExtra(EXTRA_WORD));
        }else {
            status1 = false;
        }

        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);

    }

    public void save(View view) {
        save();
        Intent intent = new Intent(AddingTask.this,MainActivity.class);
        startActivity(intent);
    }
    private void save(){
        String desc = editText.getText().toString();
        task = new Task(desc,status);
        if (desc.isEmpty()){
            Toast.makeText(AddingTask.this,"fill....",Toast.LENGTH_LONG).show();
        }if (status1){
             task.setId(id);
             task.setStatus(status);
             addViewModel.updateData(task);
        }else {
            addViewModel.insertData(task);
        }

        finish();

    }

    public void reminder(View view) {

        calendar = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMin=calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(AddingTask.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                h = String.valueOf(i);

                m = String.valueOf(i1);

                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Date dat = new Date();
                calendar.setTime(dat);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
                calendar.set(Calendar.MINUTE, Integer.parseInt(m));
                calendar.set(Calendar.SECOND,0);

                if(calendar.before(cal_now)){
                    calendar.add(Calendar.DATE,1);
                }

                Intent myIntent = new Intent(AddingTask.this, MyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(AddingTask.this, 0, myIntent, 0);

                manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);

            }
        },currentHour,currentMin,false);

        timePickerDialog.show();


    }
}