package com.example.oduva.test_app;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private Button mSend_Rating;
    private Button mWatch_Graph;
    private Button mSetting;
    private Button mExit;
    private static final String TAG = "MainMenu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(Bundle) called");
        setContentView(R.layout.activity_main_menu);

        mSend_Rating=(Button)findViewById(R.id.Send_Rating);
        mSend_Rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mWatch_Graph=(Button)findViewById(R.id.Watch_Graph);
        mWatch_Graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,Choose_period.class);
                startActivity(intent);
            }
        });

        mSetting=(Button)findViewById(R.id.Setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainMenu.this, Setting.class);
                startActivity(intent);
            }
        });
        mExit=(Button)findViewById(R.id.Exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
