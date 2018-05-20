package com.example.oduva.test_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import info.fandroid.p0341_sqlite.dbhelper;



public class GraphActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_PERIOD="com.bignerdranch.android.geoquiz.answer_is_true";
    GraphView mGraph;
    dbhelper dbhelper;
    int mPeriod;
    int [] marks;
    int [] hours;
    int wAverage []=new int[7];
    int [] mAverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper=new dbhelper(this);
        setContentView(R.layout.activity_graph);
        mGraph = (GraphView) findViewById(R.id.graph);
        mPeriod=getIntent().getIntExtra(EXTRA_ANSWER_IS_PERIOD,3);
        if(mPeriod==1){
            ShowGraphicsOfDay();
        }
        else if(mPeriod==2){
            ShowGraphicsOfWeek();
        }
        else
            ShowGraphicsOfMouth();


    }

    public static Intent newIntent(Context packageContext, int answerIsTrue) {
        Intent intent = new Intent(packageContext, GraphActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_PERIOD, answerIsTrue);
        return intent;
    }

    public void ShowGraphicsOfMouth(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        StringBuilder msgStr = new StringBuilder("");
        Format format = new SimpleDateFormat("dd.MM");
        msgStr.append(format.format(new Date()));

        String day=msgStr.toString();
        String mouth=msgStr.toString();

        day=day.substring(0,2);
        mouth=mouth.substring(4,mouth.length());

        int k=0;

        Cursor IDCount=database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);

        if(IDCount.moveToFirst()){
            int cmouth=IDCount.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
            do{
                String dayAndmouth=IDCount.getString(cmouth);
                dayAndmouth=dayAndmouth.substring(4,dayAndmouth.length());
                if(mouth.equals(dayAndmouth)){
                    k++;
                }
            }while(IDCount.moveToNext());
        }
        IDCount.close();

        marks=new int[k];
        mAverage=new int[k];

        Cursor dayOfmouth=database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(dayOfmouth.moveToFirst()){
            int Day_and_Mouth=dayOfmouth.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
            int mark=dayOfmouth.getColumnIndex(dbhelper.KEY_MARK);
            int aID=0;
            int day2=0;
            int average=0;
            int count=0;
            do {
                String cMouth=dayOfmouth.getString(Day_and_Mouth);
                String cDay=dayOfmouth.getString(Day_and_Mouth);
                cMouth=cMouth.substring(4);
                cDay=cDay.substring(0,2);

                if(cMouth.equals(mouth)){
                    if(cDay.equals(String.valueOf(day2))){
                        average+=dayOfmouth.getInt(mark);
                        count++;
                    }
                    else{
                        if(Integer.parseInt(cDay)==day2-1){
                            average+=dayOfmouth.getInt(mark);
                            count++;
                        }
                        if(count>0){
                            average=average/count;
                            mAverage[aID]=average;
                            aID++;
                            count=0;

                            average=0;
                            average+=dayOfmouth.getInt(mark);
                            count++;

                        }
                    }

                }
                day2=Integer.parseInt(cDay);

            }while(dayOfmouth.moveToNext());
            average=average/count;
            mAverage[aID]=average;
        }
    }

    public void ShowGraphicsOfWeek(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        int k=0;
        int aID=0;

        //Получаем число недели в месяце
        StringBuilder msgStr=new StringBuilder("");
        Format format = new SimpleDateFormat("FF");
        msgStr.append(format.format(new Date()));
        String nedel = msgStr.toString();
        int wCount=Integer.parseInt(nedel);

        Cursor idCount=database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(idCount.moveToFirst()){
            int cweek=idCount.getColumnIndex(dbhelper.KEY_DAY_OF_WEEK);
            do{
                int week=idCount.getInt(cweek);
                if(wCount==week){
                    k++;
                }
            }while(idCount.moveToNext());

        }
        marks=new int[k];


        idCount.close();
        Cursor weekofday=database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);

        if(weekofday.moveToFirst()){
            int day=weekofday.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
            int mark=weekofday.getColumnIndex(dbhelper.KEY_MARK);
            int week=weekofday.getColumnIndex(dbhelper.KEY_DAY_OF_WEEK);
            int day2=0;
            int average=0;
            int count=0;

            do{
                int Week=weekofday.getInt(week);
                String str = weekofday.getString(day);
                str=str.substring(0,2);
                int Day = Integer.parseInt(str);
                if(wCount==Week){
                    if(Day==day2){
                        average+=weekofday.getInt(mark);
                        count++;
                    }

                    else{
                        if(Day==day2-1){
                            average+=weekofday.getInt(mark);
                            count++;
                        }
                        if(count>0){
                            average=average/count;
                            wAverage[aID]=average;
                            aID++;
                            count=0;

                            average=0;
                            average+=weekofday.getInt(mark);
                            count++;

                        }
                    }

                }
                day2=Day;
                //Сделай тут цикл сравнений начни с недели циклом while а затем внутри него сравнивай дни, высчитывай дни

            }while(weekofday.moveToNext());
            average=average/count;
            wAverage[aID]=average;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,0),
        });
        int i=0;


        for(int j=0; j<7;j++) {
            if(wAverage[i]!=0){
            series.appendData(new DataPoint(j+1, wAverage[i]), true, 40);
            }
            i++;
        }

        mGraph.addSeries(series);
        mGraph.getViewport().setYAxisBoundsManual(true);
        mGraph.getViewport().setXAxisBoundsManual(true);
        mGraph.getViewport().setMinimalViewport(1,24,0,10);
    }

    public void ShowGraphicsOfDay(){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        int k=0;
        StringBuilder msgStr = new StringBuilder("");
        Format formatter = new SimpleDateFormat("dd.MM");
        msgStr.append(formatter.format(new Date()));
        String day = msgStr.toString();
        Cursor fine = database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(fine.moveToFirst()){
            int ID = fine.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
            do{

                String data=fine.getString(ID);
                if(data.equals(day)){
                    k++;
                }

            }while(fine.moveToNext());
        }
        fine.close();
        hours=new int[k];
        marks=new int[k];


        int i=0;
        Cursor cursor = database.query(dbhelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int time = cursor.getColumnIndex(dbhelper.KEY_TIME);
            int mark= cursor.getColumnIndex(dbhelper.KEY_MARK);
            int date= cursor.getColumnIndex(dbhelper.KEY_DAY_AND_MONTH);
            do{
                String data="";
                data=cursor.getString(date);



                if(data.equals(day)){
                String str="";
                str=cursor.getString(time);
                str=str.substring(1,3);

                marks[i]=cursor.getInt(mark);
                hours[i]=Integer.parseInt(str);
                i=i+1;}
            }while(cursor.moveToNext());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,0),
        });
        i=0;


        for(int j=0; j<k;j++) {
            series.appendData(new DataPoint(hours[i], marks[i]), true, 40);
            i++;
        }

        mGraph.addSeries(series);
        mGraph.getViewport().setYAxisBoundsManual(true);
        mGraph.getViewport().setXAxisBoundsManual(true);
        mGraph.getViewport().setMinimalViewport(1,24,0,10);
    }

}
