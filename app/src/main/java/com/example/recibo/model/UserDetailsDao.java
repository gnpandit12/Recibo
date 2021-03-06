package com.example.recibo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(UserDetails userDetails);

    @Query("SELECT * FROM user_details")
    LiveData<UserDetails> getUserDetails();


}
