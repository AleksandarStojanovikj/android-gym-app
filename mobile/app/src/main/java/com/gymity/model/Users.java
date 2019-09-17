package com.gymity.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey
    public Long id;

    @Embedded
    public Credentials credentials;

    @ColumnInfo
    public String fullName;

    @ColumnInfo
    public boolean isAdmin;

    public Users(Credentials credentials, String fullName) {
        this.credentials = credentials;
        this.fullName = fullName;
    }

    @Ignore
    public Users(Credentials credentials) {
        this.credentials = credentials;
    }
}
