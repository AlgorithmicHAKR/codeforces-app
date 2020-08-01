package com.hakr.aman.codeforcesapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
     private TextInputLayout user;
     private Button getDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user=findViewById(R.id.userhandle);
        getDetails=findViewById(R.id.getinfo);
        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,AskActivity.class);
                intent.putExtra("user",user.getEditText().getText().toString());
                startActivity(intent);
            }
        });
    }

}
