package com.gymity.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.gymity.model.User;
import com.gymity.persistance.dao.UserDao;

import java.lang.ref.WeakReference;

public class GetUserByUsernameAsyncTask extends AsyncTask<String, Void, User> {
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
    protected User doInBackground(String... strings) {
        return userDao.findByUsername(username);
    }

    @Override
    protected void onPostExecute(User user) {
        Activity activity = weakActivity.get();
        if (activity == null) {
            return;
        }

    }
}
