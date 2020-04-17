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

    private String userName;
    private String userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        //라이프 사이클 내부에서는 가장 간단하게 코드 작성할 것
        // 기본적으로 펑션을 목적을 가지고 작은 단위로 쪼갤 것

        initValues();
        setContent();
        setListener();
    }

    public void initValues() {
        restMethods = RestClient.buildHTTPClient();


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


    public void setListener() {
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
    //peony
    //123456789

    void doLogin() {
        userName = name.getText().toString();
        userPwd = password.getText().toString();
        if (!Const.isNullOrEmptyString(userName, userPwd)) {
            showLoading(true);

            restMethods.login(userName,userPwd).enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                    //모든 네트워킹에는 success /  fail 구분하여 분기할 것
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("name", userName);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        finish();

                        Log.i(TAG, "Response: " + response.body());
                    } else {
                        Toast.makeText(getApplicationContext(), "응 해킹범 꺼졍", Toast.LENGTH_SHORT).show();

                    }

                    showLoading(false);
                }

                @Override
                public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                    Log.e(TAG, "Response: " + t.getMessage());
                    showLoading(false);
                }
            });
        } else {
            if (Const.isNullOrEmptyString(name.getText().toString())) { //당연하게 String은 null / empty둘다 해야합니다.
                nameInput.setError("아이디가 입력되지 않았습니다.");
                return;
            }

            if (Const.isNullOrEmptyString(password.getText().toString())) {
                passwordInput.setError("비밀번호가 입력되지 않았습니다.");
                return;
            }

        }


    }


    //굳이 불필요 한 나눔은 지양할 것
    private void showLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            loginForm.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginForm.setVisibility(View.VISIBLE);
        }

    }
}
