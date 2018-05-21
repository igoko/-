package com.example.oduva.test_app;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Notify_Class extends AppCompatActivity {

    private NotificationManager NM;
    private final int NOTIFICATION_ID = 104;
    private TimeBroadcastReceiver mTimeBroadCastReceiver = new TimeBroadcastReceiver();
    private AlarmManager manager;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify__class);
        NM = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        ShowNotifications();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void ShowNotifications() {

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("Новое уведомление")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Прошёл час")
                .setContentText("Пора ставить оценку");


        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        NM.notify(NOTIFICATION_ID, notification);
        this.mTimeBroadCastReceiver.onReceive(this, new Intent(this, TimeBroadcastReceiver.class));
        Intent NotifIntent = MainMenu.NotifIntent(Notify_Class.this, true);
        startActivity(NotifIntent);
    }
}