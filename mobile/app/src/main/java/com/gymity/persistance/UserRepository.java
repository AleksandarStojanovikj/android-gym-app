package com.gymity.persistance;

import android.content.Context;

import com.gymity.model.User;

import androidx.room.Room;

public class UserRepository {
    private String DB_NAME = "user";
    private static UserDatabase userDatabase = null;
    private Context context;

    public UserRepository(Context context){
        if(userDatabase==null){
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
//            userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
    }

    public User getUserByUsername(final String username){
        return userDatabase.userDao().findByUsername(username);
    }
}
