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
        setContentView(R.layout.main_activity);
        getName = findViewById(R.id.getName);

        bringName();


    }

    void bringName() {
        //Intent intent = getIntent();
        String name =getIntent().getExtras().getString("name");

        if(!Const.isNullOrEmptyString(name)){
            getName.setText(name);
        }
        else{
            getName.setText("값 없으셈 null이거나 empty임 ");
        }
    }
}