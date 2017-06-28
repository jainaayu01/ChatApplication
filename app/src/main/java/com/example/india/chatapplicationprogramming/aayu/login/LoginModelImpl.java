package com.example.india.chatapplicationprogramming.aayu.login;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.india.chatapplicationprogramming.R;

/**
 * Created by india on 6/27/2017.
 */
public class LoginModelImpl implements LoginModel {
    private OnLoginFinishedListener listener;
    private static final String[] DUMMY_CREDENTIALS =new String[]{
            "test1@gmail:TEST1","test2@gmail:TEST2"
    };

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        this.listener = listener;
        new UserLoginTask(username,password).execute((Void)null);
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

            if(success){
                listener.onSuccess();
            }
            else{
               listener.onPasswordError();
            }
        }

        @Override
        protected void onCancelled() {
            listener.onCancelled();
        }
    }
}
