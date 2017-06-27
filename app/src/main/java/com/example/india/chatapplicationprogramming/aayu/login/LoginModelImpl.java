package com.example.india.chatapplicationprogramming.aayu.login;

/**
 * Created by india on 6/27/2017.
 */
public class LoginModelImpl implements LoginModel {
    private OnLoginFinishedListener listener;
    private static final String[] DUMMY_CREDENTIALS =new String[]{
            "test1:TEST1","test2:TEST2"
    };

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        this.listener = listener;

    }
}
