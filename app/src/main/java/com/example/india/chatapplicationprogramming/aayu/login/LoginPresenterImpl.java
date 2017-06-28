package com.example.india.chatapplicationprogramming.aayu.login;

import android.text.TextUtils;

import com.example.india.chatapplicationprogramming.R;

/**
 * Created by india on 6/28/2017.
 */
public class LoginPresenterImpl implements LoginPresenter,LoginModel.OnLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if(TextUtils.isEmpty(password) && !isPasswordValid(password)){
            loginView.setPasswordError(R.string.error_invalid_password);
        }
        if(TextUtils.isEmpty(username)){
            loginView.setUsernameError((R.string.error_field_required));

        }
        else if(!isEmailValid(username)){
            loginView.setUsernameError(R.string.error_invalid_email);

        }
       loginView.showProgress(true);
        loginModel.login(username,password,this);
    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        return password.length()>4;
    }

    @Override
    public void onCancelled() {
      loginView.showProgress(false);
    }

    @Override
    public void onPasswordError() {
        loginView.showProgress(false);
loginView.setPasswordError(R.string.error_incorrect_password);

    }

    @Override
    public void onSuccess() {
loginView.showProgress(false);
        loginView.successAction();
    }
}
