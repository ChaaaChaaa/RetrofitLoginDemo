package com.networkpractice.retrofitlogindemo.model;

import com.google.gson.annotations.SerializedName;

public class AccountData {
    @SerializedName("y")
    private String token;
    private String name;
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

