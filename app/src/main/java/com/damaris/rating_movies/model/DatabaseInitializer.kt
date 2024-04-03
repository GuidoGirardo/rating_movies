package com.damaris.rating_movies.model

import android.content.Context
import androidx.room.Room

object DatabaseInitializer {
    private var INSTANCE: MovieDatabase? = null

    fun getDatabase(context: Context): MovieDatabase {
        if (INSTANCE == null) {
            synchronized(MovieDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
}