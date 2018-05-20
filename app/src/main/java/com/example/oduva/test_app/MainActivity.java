package com.example.oduva.test_app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import info.fandroid.p0341_sqlite.dbhelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mAnswer;
    SeekBar mMark;
    String sMark;
    dbhelper mDbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] result= new String[1];
        result[0]="0";



        mAnswer=(Button)findViewById(R.id.OK);
        mAnswer.setOnClickListener(this);

        mMark=(SeekBar)findViewById(R.id.mark);
        mMark.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            // метод отвечает за изменения прогресса
            // булевый параметр fromUser будет true, если прогресс был изменен пользователем
            // булевый параметр fromUser будет false, если прогресс был изменен системой
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;

            }

            // никак не реагируем на начало движения индикатора прогресса
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            // когда пользователь отпустил индикатор изменения прогресса
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // значение прогресса отображаем в TextView
                int max=progress+1;
                result[0]=String.valueOf(max);
                sMark=result[0];
            }
        });



        mDbhelper=new dbhelper(this);
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        StringBuilder msgStr = new StringBuilder(" ");
        Format formatter = new SimpleDateFormat("HH:mm");
        msgStr.append(formatter.format(new Date()));
        String time=msgStr.toString();
		formatter = new SimpleDateFormat("FF dd.MM");

		int count = msgStr.length();

		msgStr.delete(0,count);

		msgStr.append(formatter.format(new Date()));
		String date = msgStr.toString();
		count = date.length();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()){
            case R.id.OK:

                //Здесь я получаю количевство часов
                Cursor cursor = database.query(dbhelper.TABLE_CONTACTS, null, null, null, null, null, null);
                //Реализация дня
                contentValues.put(dbhelper.KEY_MARK, sMark);
                contentValues.put(dbhelper.KEY_TIME, time);
                contentValues.put(dbhelper.KEY_DAY_OF_WEEK, date.substring(0,date.indexOf(' ')));
                contentValues.put(dbhelper.KEY_DAY_AND_MONTH,date.substring(count-5,count));
                database.insert(dbhelper.TABLE_CONTACTS, null, contentValues);
                /*Toast.makeText(this,"Выполнено",Toast.LENGTH_SHORT).show();*/
                break;
                //Конец Дня
        }
        }
    }

