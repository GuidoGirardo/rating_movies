package com.damaris.rating_movies.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damaris.rating_movies.model.DatabaseInitializer
import com.damaris.rating_movies.model.MovieDao
import com.damaris.rating_movies.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {

    private lateinit var context: Context
    private lateinit var movieDao: MovieDao

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>> = _movies

    fun initialize(context: Context) {
        this.context = context
        val database = DatabaseInitializer.getDatabase(context.applicationContext)
        movieDao = database.movieDao()
    }

    fun insertMovie(movieName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newMovie = MovieEntity(title = movieName, rating = 0)
                movieDao.insertMovie(newMovie)
                val updatedMovies = movieDao.getAllMovies()
                _movies.postValue(updatedMovies)
                Log.i("xd", movieDao.getAllMovies().toString())
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val movies = movieDao.getAllMovies()
                _movies.postValue(movies)
            }
        }
    }

}