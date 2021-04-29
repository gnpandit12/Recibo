package com.example.recibo.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = UserDetails.class, version = 1, exportSchema = false)
public abstract class UserDetailsDatabase extends RoomDatabase {

    private static volatile UserDetailsDatabase INSTANCE;
    public abstract UserDetailsDao userDetailsDao();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UserDetailsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDetailsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDetailsDatabase.class, "user_details")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
