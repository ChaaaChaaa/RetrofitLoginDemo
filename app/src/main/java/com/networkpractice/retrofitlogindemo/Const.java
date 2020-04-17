package com.networkpractice.retrofitlogindemo;

public class Const {


    public static boolean isNullOrEmptyString(String... msgs) {
        for (String item : msgs) {
            if (item == null || item.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
