package com.example.india.chatapplicationprogramming.aayu.login;

/**
 * Created by india on 6/27/2017.
 */
public interface LoginView {
    void showProgress(boolean progress);

    void setUsernameError(int messageResId);

    void setPasswordError(int messageResId);

    void successAction();
}
