package com.gymity.viewmodels;

import android.app.Application;

import com.gymity.model.Users;
import com.gymity.persistance.UserRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private LiveData<Users> user;
    private UserRepository userRepository = null;

    public UserViewModel(@NonNull Application application, String username) {
        super(application);
        userRepository = new UserRepository(UserViewModel.this.getApplication());
//        user = userRepository.getUserByUsername(username);
    }

    public LiveData<Users> getUserByUsername(String username){
        return user;
    }
}
