package com.networkpractice.retrofitlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getName = findViewById(R.id.getName);

        bringName();


    }

    void bringName() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        getName.setText(userName);
    }
}