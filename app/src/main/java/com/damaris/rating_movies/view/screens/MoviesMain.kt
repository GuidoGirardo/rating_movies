package com.damaris.rating_movies.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.model.MovieEntity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.damaris.rating_movies.R
import com.damaris.rating_movies.view.navigation.AppScreens
import com.damaris.rating_movies.view.ui.theme.background
import com.damaris.rating_movies.view.ui.theme.borderColor
import com.damaris.rating_movies.view.ui.theme.itemColor
import com.damaris.rating_movies.view.ui.theme.textColor

@Composable
fun MoviesMain(navController: NavController, viewModel: MovieViewModel = viewModel()) {

    var movieName by remember { mutableStateOf("") }
    viewModel.getMovies()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(vertical = 16.dp)
    ) {
        // primer elemento de la columna
        val movies by viewModel.movies.observeAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    navController.navigate("${AppScreens.MoviesDetail.route}/${movie.id}")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // segundo elemento de la columna (fijado al bottom)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp)),
                value = movieName,
                onValueChange = { movieName = it },
                placeholder = { Text("Ingrese una película") }
            )
            Spacer(modifier = Modifier.width(24.dp))
            Button(
                onClick = {
                    viewModel.insertMovie(movieName)
                },
                modifier = Modifier.background(borderColor, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    borderColor
                )
            ) {
                Text(
                    "enviar",
                    color = itemColor,
                    modifier = Modifier.background(borderColor, RoundedCornerShape(24.dp)),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieEntity, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(itemColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onItemClick)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Image(
            painter = painterResource(id = movie.coverImageResourceId),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp),
            color = textColor
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}