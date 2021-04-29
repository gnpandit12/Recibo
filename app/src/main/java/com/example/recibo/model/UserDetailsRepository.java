package com.example.recibo.model;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

public class UserDetailsRepository {

    private UserDetailsDao userDetailsDao;
    private LiveData<UserDetails> userDetailsLiveData;

    public UserDetailsRepository(Application application){
        UserDetailsDatabase database =
                UserDetailsDatabase.getDatabase(application);
        userDetailsDao = database.userDetailsDao();
        userDetailsLiveData = userDetailsDao.getUserDetails();
    }

    public LiveData<UserDetails> getUserDetails() {
        return userDetailsLiveData;
    }

    public void insert(UserDetails userDetails) {
        UserDetailsDatabase.databaseWriteExecutor.execute(() -> {
            userDetailsDao.Insert(userDetails);
        });
    }

}
