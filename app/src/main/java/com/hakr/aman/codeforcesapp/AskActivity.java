package com.hakr.aman.codeforcesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AskActivity extends AppCompatActivity {
    private TextView contest,virtual,practice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        String user=getIntent().getStringExtra("user");

        contest=(TextView) findViewById(R.id.contest);
        virtual=(TextView) findViewById(R.id.virtual);
        practice=(TextView)findViewById(R.id.practice);
        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AskActivity.this,MainActivity.class);
                intent.putExtra("category","contest");
                intent.putExtra("user",user);
                  startActivity(intent);
                  finish();
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AskActivity.this,MainActivity.class);
                intent.putExtra("category","practice");
                intent.putExtra("user",user);

                startActivity(intent);                  finish();

            }
        }); virtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AskActivity.this,MainActivity.class);
                intent.putExtra("user",user);

                intent.putExtra("category","virtual");
                startActivity(intent);                  finish();

            }
        });
    }
}
