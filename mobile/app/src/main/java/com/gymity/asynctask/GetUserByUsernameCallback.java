package com.gymity.asynctask;

import com.gymity.model.User;

public interface GetUserByUsernameCallback {
    void onLoaded(User user);
}
