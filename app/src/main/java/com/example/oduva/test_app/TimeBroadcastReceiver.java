package com.example.oduva.test_app;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class TimeBroadcastReceiver extends BroadcastReceiver {
    private AlarmManager manager;
    Setting notif;

    @Override
    public void onReceive(Context context, Intent intent) {
        manager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent fakeIntent = PendingIntent.getActivity(context,0,new Intent(context,Notify_Class.class),PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 3600);
        long time = calendar.getTimeInMillis();

        manager.setRepeating(AlarmManager.RTC, time, 1000*60*60, fakeIntent);
    }
}
