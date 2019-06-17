package com.gymity.persistance.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.gymity.model.Users;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM Users")
    List<Users> getAll();

}
