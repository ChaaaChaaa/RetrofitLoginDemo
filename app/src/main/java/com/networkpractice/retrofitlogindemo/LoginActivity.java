package com.networkpractice.retrofitlogindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();

    private AppCompatEditText name;
    private AppCompatEditText password;
    private AppCompatButton loginButton;


    private TextInputLayout nameInput;
    private TextInputLayout passwordInput;

    private ProgressBar progressBar;
    private NestedScrollView loginForm;

    private RestMethods restMethods;

    private TextView tryToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        restMethods = RestClient.buildHTTPClient();

        setContent();

        tryToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                doLogin();
            }
        });
    }

    void setContent() {
        loginButton = findViewById(R.id.name_sign_in_button);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.login_progress);
        loginForm = findViewById(R.id.login_form);
        nameInput = findViewById(R.id.input_name);
        passwordInput = findViewById(R.id.input_password);
        tryToRegister = findViewById(R.id.signup_go);
    }


    void doLogin() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            nameInput.setError("아이디가 입력되지 않았습니다.");
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            passwordInput.setError("비밀번호가 입력되지 않았습니다.");
            return;
        }


        showLoading();
        restMethods.login(name.getText().toString(), password.getText().toString()).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                Log.i(TAG, "Response: " + response.body());
                hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.e(TAG, "Response: " + t.getMessage());
                hideLoading();
            }
        });
    }


    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
    }
}
