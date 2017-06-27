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
public class LoginActivity extends AppCompatActivity {
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test1@gmail:TEST1", "test2:TEST2"
    };
    private UserLoginTask mAuthTask = null;
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

    }
    protected void attemptLogin(){
        if(mAuthTask!=null)
            return;
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if(TextUtils.isEmpty(password) && !isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if(TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel=true;
        }
        else if(!isEmailValid(email)){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel= true;
        }
        if(cancel){
            focusView.requestFocus();
        }
        else{
            showProgress(true);
            mAuthTask = new UserLoginTask(email,password);
            mAuthTask.execute((Void)null);
        }

    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        return password.length()>4;
    }

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

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String mEmail, String mPassword) {
            this.mEmail = mEmail;
            this.mPassword = mPassword;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);

            } catch (InterruptedException exc) {
                return false;

            }
            for (String credentials : DUMMY_CREDENTIALS) {
                String[] pieces = credentials.split(":");
                if (pieces[0].equals(mEmail)) {
                    return pieces[1].equals(mPassword);
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
             showProgress(false);
            if(success){
                Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }
            else{
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
