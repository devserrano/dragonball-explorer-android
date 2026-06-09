package com.devserrano.dragonballexplorer.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        if (database == null) {

            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dragon_ball_database"
            ).build()

        }

        return database!!
    }
}