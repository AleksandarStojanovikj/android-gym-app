package com.gymity.asynctask;

import com.gymity.model.Users;

public interface GetUserByUsernameCallback {
    void onLoaded(Users user);
}
