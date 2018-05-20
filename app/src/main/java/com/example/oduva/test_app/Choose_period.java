package com.example.oduva.test_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Choose_period extends AppCompatActivity implements View.OnClickListener {
    Button mDay;
    Button mWeek;
    Button mMouth;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_period);

        mTextView=(TextView)findViewById(R.id.kitty);

        mDay=(Button)findViewById(R.id.Choose_day);
        mDay.setOnClickListener(this);
        mWeek=(Button)findViewById(R.id.Choose_week);
        mWeek.setOnClickListener(this);
        mMouth=(Button)findViewById(R.id.Choose_mouth);
        mMouth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Choose_day:
                Intent intent = GraphActivity.newIntent(Choose_period.this,1);
                startActivity(intent);
                break;
            case R.id.Choose_week:
                Intent intent1 = GraphActivity.newIntent(Choose_period.this,2);
                startActivity(intent1);
                break;
            case R.id.Choose_mouth:
                Intent intent2 = GraphActivity.newIntent(Choose_period.this,3);
                startActivity(intent2);
                break;
        }
    }

}
