package com.networkpractice.retrofitlogindemo;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;



public class SharedPreferenceConfig {
//    public static SharedPreferenceConfig INSTANCE;
//    private final String PREFERENCE_FILE = "User";
//    public final String PREFERENCE_TOKEN = "pref_user_token";
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;
//
//    private SharedPreferenceConfig() {
//        Application app = LoginActivity.getInstance();
//        sharedPreferences = app.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//    }
//
//    public static SharedPreferenceConfig getInstance(){
//        if (INSTANCE == null)
//            INSTANCE = new SharedPreferenceConfig();
//        return INSTANCE;
//    }
//
//    public String getToken(){
//        return SharedPreferenceConfig.getInstance().sharedPreferences.getString(PREFERENCE_TOKEN,"");
//    }
//
//    public void saveToken(String value){
//        editor.putString(PREFERENCE_TOKEN,value);
//        editor.apply();
//    }



//    public SharedPreferenceConfig(Context context){
//        this.context = context;
//        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);
//    }
//
//    public void writeLoginStatus(boolean status){
//
//        editor = sharedPreferences.edit();
//        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
//        sharedPreferences.edit().putString("token", hawkerauthToken).commit();
//    }
//
//    public boolean readLoginStatus(){
//        boolean status = false;
//        String token = sharedPreferences.getString("token","");
//        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
//        return status;
//    }


}
