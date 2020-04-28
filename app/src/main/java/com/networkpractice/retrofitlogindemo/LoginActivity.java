package com.networkpractice.retrofitlogindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.networkpractice.retrofitlogindemo.model.AccountData;
import com.networkpractice.retrofitlogindemo.model.LoginData;
import com.networkpractice.retrofitlogindemo.databinding.LoginActivityBinding;
import com.networkpractice.retrofitlogindemo.service.RestClient;
import com.networkpractice.retrofitlogindemo.service.RestMethods;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();

    private static LoginActivity instance;
    private SharedPreferenceConfig preferenceConfig;

    private AppCompatEditText name;
    private AppCompatEditText password;


    private TextInputLayout nameInput;
    private TextInputLayout passwordInput;

    private ProgressBar progressBar;
    private NestedScrollView loginForm;

    private RestMethods restMethods;

    private String userName;

    LoginActivityBinding loginActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.login_activity);
        //preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        loginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        loginActivityBinding.setActivity(this);

        //라이프 사이클 내부에서는 가장 간단하게 코드 작성할 것
        // 기본적으로 펑션을 목적을 가지고 작은 단위로 쪼갤 것

        initValues();
        setContent();
    }

    public void initValues() {
        restMethods = RestClient.buildHTTPClient();
    }


    void setContent() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.login_progress);
        loginForm = findViewById(R.id.login_form);
        nameInput = findViewById(R.id.input_name);
        passwordInput = findViewById(R.id.input_password);
    }

//    void checkSharedPreference(){
//        if(preferenceConfig.readLoginStatus()){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }


    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.tryToRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            case R.id.loginButton:
                doLogin();
        }
    }

    //peony
    //123456789

    void doLogin() {
        userName = Objects.requireNonNull(name.getText()).toString();
        String userPwd = Objects.requireNonNull(password.getText()).toString();
        if (!Const.isNullOrEmptyString(userName, userPwd)) {
            showLoading(true);

            restMethods.login(userName, userPwd).enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                    //모든 네트워킹에는 success /  fail 구분하여 분기할 것
                    if (response.isSuccessful()) {
                        LoginData loginData = response.body();
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("name", userName);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        //preferenceConfig.writeLoginStatus(true);
                        finish();

                        Log.i(TAG, "Response: " + response.body());
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    }

                    showLoading(false);
                }

                @Override
                public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                    Log.e("sdfsdfdfs",t.getLocalizedMessage());
                }
            });
        } else {
            checkNullOrEmpty();
        }
    }

    private void checkNullOrEmpty() {
        if (Const.isNullOrEmptyString(Objects.requireNonNull(name.getText()).toString())) {
            nameInput.setError("아이디가 입력되지 않았습니다.");
            return;
        }

        if (Const.isNullOrEmptyString(Objects.requireNonNull(password.getText()).toString())) {
            passwordInput.setError("비밀번호가 입력되지 않았습니다.");
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

    public static LoginActivity getInstance(){
        return instance;
    }


}