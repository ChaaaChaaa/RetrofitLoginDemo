package com.networkpractice.retrofitlogindemo.model;

public class LoginData {
    //    String userName;
//    String password;
    String message;
    Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    static class Data {
        String token;
    }
}
