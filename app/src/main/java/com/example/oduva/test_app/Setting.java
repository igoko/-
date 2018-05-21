package com.example.oduva.test_app;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import info.fandroid.p0341_sqlite.dbhelper;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    Button mClear,mInsruction,mTeam,mButton;
    dbhelper mDbhelper;
    private final int NOTIFICATION_ID = 104;
    private AlarmManager manager;
    public NotificationManager NM;
    private TimeBroadcastReceiver mTimeBroadCastReceiver = new TimeBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mClear=(Button)findViewById(R.id.Clear);
        mClear.setOnClickListener(this);

        mInsruction=(Button)findViewById(R.id.Instruction);
        mInsruction.setOnClickListener(this);

        mTeam=(Button)findViewById(R.id.Inform_Team);
        mTeam.setOnClickListener(this);

        mDbhelper= new dbhelper(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.Clear:
                database.delete(mDbhelper.TABLE_CONTACTS, null, null);
                break;
            case R.id.Instruction:

                break;
            case R.id.Inform_Team:
                Intent intent1 = new Intent(this, Text_activity.class);
                intent1.putExtra("IN",false);
                startActivity(intent1);
                break;
            case R.id.button:
               /* Cursor fine = database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);
                int ID1, k,t1,t2;
                t1=60;
                t2=60;
                String time1,time2;
                k=1;
                if(fine.moveToFirst()){
                    int name = fine.getColumnIndex(dbhelper.KEY_TIME);
                    int ID = fine.getColumnIndex(dbhelper.KEY_ID);
                    do {

                        String time = fine.getString(name);


                        if(k%2==1){
                            time1 = time;
                            time1=time1.substring(4,6);
                            t1 = Integer.parseInt(time1);

                            if (k>1){
                                if(t1<t2){
                                Toast.makeText(this,time1,Toast.LENGTH_SHORT).show();
                            }
                            }

                        }
                        else if (k%2==0){
                            time2 = time;
                            time2=time2.substring(4,6);
                            t2 = Integer.parseInt(time2);

                            if(t2<t1){
                                Toast.makeText(this,time2,Toast.LENGTH_SHORT).show();
                            }
                        }

                        k++;

                    }while(fine.moveToNext());
                }
                fine.close();
                Cursor cursor = database.query(dbhelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(dbhelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(dbhelper.KEY_MARK);
                    int timeIndex = cursor.getColumnIndex(dbhelper.KEY_TIME);
                    int weekIndex = cursor.getColumnIndex(dbhelper.KEY_DAY_OF_WEEK);
                    int monthIndex= cursor.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
                    do {
                        Toast.makeText(this, "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) + cursor.getString(timeIndex)+" "+cursor.getString(weekIndex)+" "+ cursor.getString(monthIndex), Toast.LENGTH_SHORT).show();

                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex));
                    } while (cursor.moveToNext());
                }
                cursor.close();*/
                startActivity(new Intent (this, Notify_Class.class));
                finish();
                break;
        }

    }

}
