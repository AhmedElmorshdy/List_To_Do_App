package com.mmabas77.listtodoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();

        MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.songs);
        mediaPlayer.start();
    }
}