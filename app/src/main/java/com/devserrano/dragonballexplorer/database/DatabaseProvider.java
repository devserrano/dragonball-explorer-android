package com.devserrano.dragonballexplorer.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {

    private static AppDatabase database;

    public static AppDatabase getDatabase(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "dragon_ball_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }
}