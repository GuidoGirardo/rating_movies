package com.damaris.rating_movies.view.screens

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.model.DatabaseInitializer
import com.damaris.rating_movies.model.MovieEntity
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MoviesMain(navController: NavController, viewModel: MovieViewModel = viewModel()) {

    var movieName by remember { mutableStateOf("") }

    TextField(
        value = movieName,
        onValueChange = { movieName = it },
        placeholder = { Text("Ingrese el nombre de la película") }
    )

    Button(
        onClick = {
            viewModel.insertMovie(movieName)
        }
    ){
        Text("enviar")
    }

}