package edu.co.eci.roomandroid;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public User(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public User() {
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}