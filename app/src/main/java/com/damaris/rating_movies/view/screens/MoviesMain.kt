package com.damaris.rating_movies.view.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.model.MovieEntity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.damaris.rating_movies.view.navigation.AppScreens

/* @Preview
@Composable
fun MoviesMainPreview() {
    val fakeNavController = rememberNavController()
    val fakeViewModel = MovieViewModel()
    fakeViewModel.getMovies()
    MoviesMain(navController = fakeNavController, viewModel = fakeViewModel)
} */

@Composable
fun MoviesMain(navController: NavController, viewModel: MovieViewModel = viewModel()) {

    var movieName by remember { mutableStateOf("") }
    viewModel.getMovies()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val movies by viewModel.movies.observeAsState(initial = emptyList())
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    navController.navigate("${AppScreens.MoviesDetail.route}/${movie.id}")
                }
            }
        }

    }
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row {
            TextField(
                modifier = Modifier.weight(1f),
                value = movieName,
                onValueChange = { movieName = it },
                placeholder = { Text("Ingrese el nombre de la película") }
            )

            Button(
                onClick = {
                    viewModel.insertMovie(movieName)
                }
            ) {
                Text("enviar")
            }
        }
    }

}



@Composable
fun MovieItem(movie: MovieEntity, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview
@Composable
fun MovieItemPreview() {
    val movie = MovieEntity(1, "Ejemplo de Película", 3)
    MovieItem(movie = movie) {}
}
