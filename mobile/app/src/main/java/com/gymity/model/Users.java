package com.gymity.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey
    public Long id;

    @Embedded
    public Credentials credentials;

    @ColumnInfo
    public String fullName;

    public Users(Credentials credentials, String fullName){
        this.credentials = credentials;
        this.fullName = fullName;
    }
}
