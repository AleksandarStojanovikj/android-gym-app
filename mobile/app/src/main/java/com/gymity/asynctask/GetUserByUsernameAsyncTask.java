package com.gymity.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.gymity.model.Users;
import com.gymity.persistance.dao.UserDao;

import java.lang.ref.WeakReference;

public abstract class GetUserByUsernameAsyncTask extends AsyncTask<String, Void, Users> {
    private WeakReference<Activity> weakActivity;
    private String username;
    private String password;
    private UserDao userDao;

    public GetUserByUsernameAsyncTask(Activity activity, String username, String password, UserDao userDao) {
        weakActivity = new WeakReference<>(activity);
        this.password = password;
        this.username = username;
        this.userDao = userDao;
    }


    @Override
    protected void onPostExecute(Users user) {
        Activity activity = weakActivity.get();
        if (activity == null) {
            return;
        }

    }
}
