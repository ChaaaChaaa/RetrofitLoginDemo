package com.networkpractice.retrofitlogindemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView getName;
    private SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        getName = findViewById(R.id.getName);

        bringName();


    }

    void bringName() {

        String name = Objects.requireNonNull(getIntent().getExtras()).getString("name");

        if (!Const.isNullOrEmptyString(name)) {
            getName.setText(name);
        } else {
            getName.setText("there is no value (null or empty) ");
        }
    }

    public void userLogOut(View view){
        //preferenceConfig.writeLoginStatus(false);
        Intent intent = new Intent(MainActivity.this,  LoginActivity.class);
        startActivity(intent);
        finish();
    }


}