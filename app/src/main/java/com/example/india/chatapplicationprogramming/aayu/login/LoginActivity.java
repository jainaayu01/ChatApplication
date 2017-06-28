package com.example.india.chatapplicationprogramming.aayu.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.india.chatapplicationprogramming.R;

/**
 * Created by india on 6/26/2017.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter loginPresenter;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgessView;
    private View mLoginFormView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgessView = findViewById(R.id.login_progress);
          loginPresenter = new LoginPresenterImpl(this);
    }
    protected void attemptLogin(){

        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
       loginPresenter.validateCredentials(email,password);

    }

  @Override
    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
           mProgessView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgessView.animate().setDuration(shortAnimTime).alpha(show?1:0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgessView.setVisibility(show?View.VISIBLE:View.GONE);
            }
        });
    }

    @Override
    public void setUsernameError(int messageResId) {
      mEmailView.setError(getString(messageResId));
        mEmailView.requestFocus();
    }

    @Override
    public void setPasswordError(int messageResId) {
            mPasswordView.setError(getString(messageResId));
        mPasswordView.requestFocus();
    }

    @Override
    public void successAction() {
   Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
    }


}
