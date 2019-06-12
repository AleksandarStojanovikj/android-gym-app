package com.gymity.persistance.dao;

import com.gymity.model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username=:username")
    User findByUsername(String username);
}
