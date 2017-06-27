package com.example.india.chatapplicationprogramming.aayu.login;

/**
 * Created by india on 6/27/2017.
 */
public interface LoginModel {
    interface OnLoginFinishedListener{
        void onCancelled();
        void onPasswordError();
        void onSuccess();

    }
    void login(String username , String password, OnLoginFinishedListener listener);
}
