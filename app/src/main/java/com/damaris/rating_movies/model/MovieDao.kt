package com.damaris.rating_movies.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): MovieEntity?

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert
    fun insertMovie(movie: MovieEntity)

    @Query("UPDATE movies SET rating = :rating WHERE id = :movieId")
    fun changeMovieRating(movieId: Int, rating: Int)
}