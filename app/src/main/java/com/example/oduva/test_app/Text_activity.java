package com.example.oduva.test_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Text_activity extends AppCompatActivity {
    TextView textViewInstructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_activity);
        textViewInstructions=(TextView)findViewById(R.id.Instruction);


    }

    void showResult(){

    }
    void showIntsruction(){

    }
}
