package com.example.recibo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_details")
public class UserDetails {

    @PrimaryKey
    private int id;

//    @ColumnInfo(name = "first_name")
    private String firstName;
//    @ColumnInfo(name = "last_name")
    private String lastName;
//    @ColumnInfo(name = "mobile_number")
    private String mobileNumber;

    public UserDetails() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
