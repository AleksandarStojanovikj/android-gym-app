package com.gymity.persistance;

import com.gymity.model.Users;
import com.gymity.persistance.dao.UserDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
