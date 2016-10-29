package com.smartrecycling.smartrecycling;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 10/29/2016.
 */

public class UserLocalStore {
    public static final String SP_NAME = "userDetails";         //Tên của Share Preferences
    SharedPreferences userLocalDatabase;
/*
    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    //Lưu user vô bộ nhớ
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.m_name);
        spEditor.putString("avatar",user.m_avatar);
        spEditor.putString("email", user.m_email);
        spEditor.commit();
    }

    //Lấy thông tin user từ bộ nhớ
    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name","");
        String avatar = userLocalDatabase.getString("avatar","");
        String email = userLocalDatabase.getString("email","");

        User storedUser = new User(name, avatar, email);
        return storedUser;
    }

    //Set thông tin là app đã được đăng nhập hay chưa?
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    //Lấy thông tin về việc đăng nhập của app
    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn",false) == true){
            return true;
        }
        else{
            return false;
        }

    }

    //Xóa toàn bộ thông tin trong bộ nhớ
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }*/
}
