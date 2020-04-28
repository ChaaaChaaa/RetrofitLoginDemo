package com.networkpractice.retrofitlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.textfield.TextInputLayout;
import com.networkpractice.retrofitlogindemo.model.RegisterData;
import com.networkpractice.retrofitlogindemo.service.RestClient;
import com.networkpractice.retrofitlogindemo.service.RestMethods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements Button.OnClickListener {
    private String TAG = RegisterActivity.class.getSimpleName();

    // 아악 아악 findviewBy ID!!!!!!!!!!!!!!!!
    //ㅋㅋㅋ 괜찮아요 편한대로 쓰세요, 두분 다 다만 데바쓰는게 조금 많이 편해질거에요.

    AppCompatEditText name;
    AppCompatEditText password;
    AppCompatButton registerButton;

    TextInputLayout nameInput;
    TextInputLayout passwordInput;

    ProgressBar progressBar;
    NestedScrollView registerForm;

    RestMethods restMethods;

    private String registerName;
    private String registerPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        restMethods = RestClient.buildHTTPClient();
        setContent();
        registerButton.setOnClickListener(this);
    }

    void setContent() {
        registerButton = findViewById(R.id.register_button);
        name = findViewById(R.id.name_register);
        password = findViewById(R.id.password_register);
        progressBar = findViewById(R.id.register_progress);
        registerForm = findViewById(R.id.register_form);
        nameInput = findViewById(R.id.input_name_register);
        passwordInput = findViewById(R.id.input_password_register);
    }

    @Override
    public void onClick(View view) {
        doRegister();
    }


    void doRegister() {
        registerName = name.getText().toString();
        registerPwd = password.getText().toString();

        if (!Const.isNullOrEmptyString(registerName, registerPwd)) {
            showLoading(true);
            restMethods.register(registerName, registerPwd).enqueue(new Callback<RegisterData>() {
                @Override
                public void onResponse(@NonNull Call<RegisterData> call, @NonNull Response<RegisterData> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Log.i(TAG, "Response: " + response.body());
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                    }
                    showLoading(false);
                }

                @Override
                public void onFailure(@NonNull Call<RegisterData> call, @NonNull Throwable t) {
                }

            });
        } else {
            checkNullOrEmpty();
        }
    }

    private void checkNullOrEmpty() {
        if (Const.isNullOrEmptyString(name.getText().toString())) {
            nameInput.setError("아이디가 입력되지 않았습니다.");
            return;
        }

        if (Const.isNullOrEmptyString(password.getText().toString())) {
            passwordInput.setError("비밀번호가 입력되지 않았습니다.");
            return;
        }
    }


    private void showLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            registerForm.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            registerForm.setVisibility(View.VISIBLE);
        }

    }
}