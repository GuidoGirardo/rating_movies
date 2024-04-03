package com.damaris.rating_movies.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert
    fun insertMovie(movie: MovieEntity)
}